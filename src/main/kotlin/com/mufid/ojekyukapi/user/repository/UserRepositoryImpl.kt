package com.mufid.ojekyukapi.user.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mufid.ojekyukapi.database.DatabaseComponent
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.utils.handler.OjekyukException
import com.mufid.ojekyukapi.utils.toResult
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    @Autowired
    private val databaseComponent: DatabaseComponent
) : UserRepository {

    private fun getCollection(): MongoCollection<User> {
        return databaseComponent.database.getDatabase("ojekyuk")
            .getCollection()
    }

    override fun insertUser(user: User): Result<Boolean> {
        val existingUser = getUserByUsername(user.username)
        return if (existingUser.isSuccess) {
            throw OjekyukException("User is exist")
        } else {
            getCollection().insertOne(user).wasAcknowledged().toResult()
        }
    }

    override fun getUserById(id: String): Result<User> {
        return getCollection().findOne(User::id eq id).toResult()
    }

    override fun getUserByUsername(username: String): Result<User> {
        return getCollection().findOne(User::username eq username).toResult("User with $username not found!")
    }

    override fun updateUser(id: String, user: User): Result<Boolean> {
        println("User => $user")
        val existingUser = getCollection().findOne(User::id eq id)

        // check username is Exist
        val existingUsername = getCollection().findOne(User::username eq user.username)
        if (existingUsername != null) {
            if (existingUsername.id == existingUser?.id) {
                return doUpdate(
                    User(
                        id = existingUser.id,
                        username = user.username,
                        password = user.password.ifEmpty { existingUser.password },
                        firstName = user.firstName,
                        lastName = user.lastName,
                        phoneNumber = user.phoneNumber,
                        email = user.email,
                        role = existingUser.role
                    )
                )
            } else {
                throw OjekyukException("Username is already exist!")
            }
        } else {
            return doUpdate(
                User(
                    id = existingUser?.id ?: "",
                    username = user.username,
                    password = user.password.ifEmpty { existingUser?.password ?: "" },
                    firstName = user.firstName,
                    lastName = user.lastName,
                    phoneNumber = user.phoneNumber,
                    email = user.email,
                    role = existingUser?.role ?: 1
                )
            )
        }
    }

    private fun doUpdate(user: User): Result<Boolean> {
        return getCollection().updateOne(User::id eq user.id, user).wasAcknowledged().toResult()
    }
}