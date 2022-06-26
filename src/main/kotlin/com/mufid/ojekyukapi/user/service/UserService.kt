package com.mufid.ojekyukapi.user.service

import com.mufid.ojekyukapi.user.entity.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.UserLogin

interface UserService {
    fun getLogin(userLogin: UserLogin): Result<LoginResponse>
    fun register(user: User): Result<Boolean>
    fun getUserById(id: String): Result<User>
    fun getUserByUsername(username: String): Result<User>
}