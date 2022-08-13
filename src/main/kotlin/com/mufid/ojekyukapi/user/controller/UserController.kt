package com.mufid.ojekyukapi.user.controller

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.response.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.request.CustomerRegisterRequest
import com.mufid.ojekyukapi.user.entity.request.DriverRegisterRequest
import com.mufid.ojekyukapi.user.entity.request.UserLoginRequest
import com.mufid.ojekyukapi.user.service.UserService
import com.mufid.ojekyukapi.utils.asResponse
import com.mufid.ojekyukapi.utils.findUserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/driver/register")
    fun driverRegister(
        @RequestBody req: DriverRegisterRequest
    ): BaseResponse<Boolean> {
        return userService.register(req.mapToUser()).asResponse()
    }

    @PostMapping("/customer/register")
    fun customerRegister(
        @RequestBody req: CustomerRegisterRequest
    ): BaseResponse<Boolean> {
        return userService.register(req.mapToUser()).asResponse()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody req: UserLoginRequest
    ): BaseResponse<LoginResponse> {
        return userService.login(req).asResponse()
    }

    @GetMapping
    fun getUser(): BaseResponse<User> {
        val userId = findUserId()
        return userService.getUserById(userId.orEmpty()).asResponse()
    }

    @PutMapping("/coordinate")
    fun updateCoordinate(
        @RequestBody req: Coordinate
    ): BaseResponse<Boolean> {
        val userId = findUserId()
        return userService.updateUserCoordinate(userId, req).asResponse()
    }
}