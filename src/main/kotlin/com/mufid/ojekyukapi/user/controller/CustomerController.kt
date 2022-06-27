package com.mufid.ojekyukapi.user.controller

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.user.entity.response.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.request.UserLoginRequest
import com.mufid.ojekyukapi.user.entity.request.UserRegisterRequest
import com.mufid.ojekyukapi.user.service.UserService
import com.mufid.ojekyukapi.utils.asResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customer")
class CustomerController {
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
        @RequestBody userRegisterRequest: UserRegisterRequest
    ): BaseResponse<Boolean> {
        return userService.register(userRegisterRequest.mapToNewCustomer()).asResponse()
    }

    @GetMapping
    fun getCustomerInfo(): BaseResponse<User> {
        // get id user by token
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userService.getUserById(userId).asResponse()
    }
}