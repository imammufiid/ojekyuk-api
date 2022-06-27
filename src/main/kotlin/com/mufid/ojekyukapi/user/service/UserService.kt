package com.mufid.ojekyukapi.user.service

import com.mufid.ojekyukapi.user.entity.response.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.request.UserLoginRequest

interface UserService {
    fun login(userLoginRequest: UserLoginRequest): Result<LoginResponse>
    fun register(user: User): Result<Boolean>
    fun getUserById(id: String): Result<User>
    fun getUserByUsername(username: String): Result<User>
}