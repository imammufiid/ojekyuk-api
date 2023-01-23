package com.mufid.ojekyukapi.user.repository

import com.mufid.ojekyukapi.database.DatabaseComponent
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.extra.CustomerExtra
import com.mufid.ojekyukapi.user.entity.extra.DriverExtra
import com.mufid.ojekyukapi.utils.DataQuery
import com.mufid.ojekyukapi.utils.RoleEnum
import com.mufid.ojekyukapi.utils.extension.collection
import com.mufid.ojekyukapi.utils.extension.combineUpdate
import com.mufid.ojekyukapi.utils.extension.distanceFrom
import com.mufid.ojekyukapi.utils.extension.safeCastTo
import com.mufid.ojekyukapi.utils.handler.OjekyukException
import com.mufid.ojekyukapi.utils.toResult
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    @Autowired
    private val databaseComponent: DatabaseComponent
) : UserRepository {

    override fun insertUser(user: User): Result<Boolean> {
        val existingUser = getUserByUsername(user.username)
        return if (existingUser.isSuccess) {
            throw OjekyukException("User is exist")
        } else {
            databaseComponent.collection<User>()
                .insertOne(user)
                .wasAcknowledged()
                .toResult()
        }
    }

    override fun getUserById(id: String): Result<User> {
        return databaseComponent.collection<User>()
            .findOne(User::id eq id)
            .run {
                if (this?.role == RoleEnum.DRIVER) {
                    this.extras.safeCastTo(DriverExtra::class.java)
                } else {
                    this?.extras?.safeCastTo(CustomerExtra::class.java)
                }
                this
            }
            .toResult()
    }

    override fun getUserByUsername(username: String): Result<User> {
        return databaseComponent.collection<User>()
            .findOne(User::username eq username)
            .toResult("User with $username not found!")
    }

    override fun <T> update(id: String?, vararg updater: DataQuery<User, T>): Result<Boolean> {
        val fields = updater.toList().combineUpdate()
        return databaseComponent.collection<User>()
            .updateOne(
                User::id eq id,
                fields
            ).wasAcknowledged()
            .toResult()
    }

    override fun findDriversByCoordinate(coordinate: Coordinate): Result<List<User>> {
        return databaseComponent.collection<User>()
            .find(User::role eq RoleEnum.DRIVER)
            .filter {
                it.location.distanceFrom(coordinate).apply {
                    println("distance from ${it.id} -> $this")
                } <= 3000.0
            }
            .toResult()
    }

    override fun updateFcmToken(id: String, fcmToken: String): Result<User> {
        databaseComponent.collection<User>()
            .updateOne(User::id eq id, User::fcmToken setTo fcmToken)
            .toResult()
        return getUserById(id)
    }

    override fun updateDriverActive(id: String, isDriverActive: Boolean): Result<User> {
        databaseComponent.collection<User>()
            .updateOne(User::id eq id, User::extras / DriverExtra::isActive setTo isDriverActive)
            .toResult()
        return getUserById(id)
    }
}