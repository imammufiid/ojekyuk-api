package com.mufid.ojekyukapi.user.repository

import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.User

interface UserRepository {
    fun insertUser(user: User): Result<Boolean>

    fun getUserById(id: String): Result<User>

    fun getUserByUsername(username: String): Result<User>

    fun updateUser(id: String, user: User): Result<Boolean>

    fun updateCoordinate(id: String, coordinate: Coordinate): Result<Boolean>
}