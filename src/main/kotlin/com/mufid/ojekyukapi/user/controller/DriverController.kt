package com.mufid.ojekyukapi.user.controller

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.response.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.request.UserLoginRequest
import com.mufid.ojekyukapi.user.entity.request.UserRequest
import com.mufid.ojekyukapi.user.service.UserService
import com.mufid.ojekyukapi.utils.asResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/driver")
class DriverController {
    @Autowired
    private lateinit var userService: UserService

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
        return userService.register(userRequest.mapToNewDriver()).asResponse()
    }

    @GetMapping
    fun getDriverInfo(): BaseResponse<User> {
        // get id user by token
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userService.getUserById(userId).asResponse()
    }

    @PutMapping
    fun updateDriver(
        @RequestBody user: UserRequest
    ): BaseResponse<Boolean> {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userService.updateUser(userId, user.mapToDriver()).asResponse()
    }

    @PutMapping("/coordinate")
    fun updateDriverCoordinate(
        @RequestBody coordinate: Coordinate
    ): BaseResponse<Boolean> {
        val id = SecurityContextHolder.getContext().authentication.principal as String
        return userService.updateUserCoordinate(id, coordinate).asResponse()
    }
}