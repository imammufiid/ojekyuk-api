package com.mufid.ojekyukapi.user.service

import com.mufid.ojekyukapi.authentication.JwtConfig
import com.mufid.ojekyukapi.user.entity.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.UserLogin
import com.mufid.ojekyukapi.user.repository.UserRepository
import com.mufid.ojekyukapi.utils.handler.OjekyukException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    @Autowired
    private val userRepository: UserRepository
): UserService {
    override fun getLogin(userLogin: UserLogin): Result<LoginResponse> {
        val resultUser = userRepository.getUserByUsername(userLogin.username)
        return resultUser.map {
            if (it.password == userLogin.password) {
                LoginResponse(JwtConfig.generateToken(it))
            } else {
                throw OjekyukException("Password not match!")
            }
        }
    }

    override fun register(user: User): Result<Boolean> {
        return userRepository.insertUser(user)
    }

    override fun getUserById(id: String): Result<User> {
        return userRepository.getUserById(id).map {
            it.password = "xxxxxx"
            it
        }
    }

    override fun getUserByUsername(username: String): Result<User> {
        return userRepository.getUserByUsername(username).map {
            it.password = "xxxxxx"
            it
        }
    }
}