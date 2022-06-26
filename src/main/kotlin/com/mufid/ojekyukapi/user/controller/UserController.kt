package com.mufid.ojekyukapi.user.controller

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.user.entity.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.UserLogin
import com.mufid.ojekyukapi.user.entity.UserRequest
import com.mufid.ojekyukapi.user.service.UserService
import com.mufid.ojekyukapi.utils.asResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun getUser(): BaseResponse<User> {
        // get id user by token
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userService.getUserById(userId).asResponse()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody userLogin: UserLogin
    ): BaseResponse<LoginResponse> {
        return userService.getLogin(userLogin).asResponse()
    }

    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: UserRequest
    ): BaseResponse<Boolean> {
        return userService.register(userRequest.mapToNewUser()).asResponse()
    }
}