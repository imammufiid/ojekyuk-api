package com.mufid.ojekyukapi.user.controller

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.user.entity.response.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.request.UserLoginRequest
import com.mufid.ojekyukapi.user.entity.request.UserRequest
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
        @RequestBody userLoginRequest: UserLoginRequest
    ): BaseResponse<LoginResponse> {
        return userService.login(userLoginRequest).asResponse()
    }

    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: UserRequest
    ): BaseResponse<Boolean> {
        return userService.register(userRequest.mapToNewUser()).asResponse()
    }
}