package com.mufid.ojekyukapi.user.repository

import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.utils.DataQuery

interface UserRepository {
    fun insertUser(user: User): Result<Boolean>

    fun getUserById(id: String): Result<User>

    fun getUserByUsername(username: String): Result<User>

    fun <T> update(id: String?, vararg updater: DataQuery<User, T>): Result<Boolean>

    fun findDriversByCoordinate(coordinate: Coordinate): Result<List<User>>
    fun updateFcmToken(id: String, fcmToken: String): Result<User>

    fun updateDriverActive(id: String, isDriverActive: Boolean): Result<User>
}