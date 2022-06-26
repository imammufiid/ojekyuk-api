package com.mufid.ojekyukapi.user.repository

import com.mongodb.client.MongoCollection
import com.mufid.ojekyukapi.database.DatabaseComponent
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.utils.handler.OjekyukException
import com.mufid.ojekyukapi.utils.toResult
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
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
}