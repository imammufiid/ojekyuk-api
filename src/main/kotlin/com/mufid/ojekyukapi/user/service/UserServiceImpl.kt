package com.mufid.ojekyukapi.user.service

import com.mufid.ojekyukapi.authentication.JwtConfig
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.response.LoginResponse
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.UserLocation
import com.mufid.ojekyukapi.user.entity.extra.DriverExtra
import com.mufid.ojekyukapi.user.entity.request.UserLoginRequest
import com.mufid.ojekyukapi.user.repository.UserLocationRepository
import com.mufid.ojekyukapi.user.repository.UserRepository
import com.mufid.ojekyukapi.utils.RoleEnum
import com.mufid.ojekyukapi.utils.extension.safeCastTo
import com.mufid.ojekyukapi.utils.extension.to
import com.mufid.ojekyukapi.utils.handler.OjekyukException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val userLocationRepository: UserLocationRepository
): UserService {
    override fun login(userLoginRequest: UserLoginRequest): Result<LoginResponse> {
        val resultUser = userRepository.getUserByUsername(userLoginRequest.username)
        return resultUser.map {
            if (it.password == userLoginRequest.password) {
                LoginResponse(JwtConfig.generateToken(it))
            } else {
                throw OjekyukException("Password invalid!")
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

    override fun updateUserCoordinate(id: String?, coordinate: Coordinate): Result<Boolean> {
        return userRepository.update(id, User::location to coordinate)
    }

    override fun updateFcmToken(id: String, fcmToken: String): Result<User> {
        return userRepository.updateFcmToken(id, fcmToken)
            .map {
                it.password = ""
                it
            }
    }

    override fun updateDriverActive(id: String, isDriverActive: Boolean): Result<User> {
        return userRepository.updateDriverActive(id, isDriverActive)
    }

    override fun updateUserLocation(id: String, coordinate: Coordinate): Result<UserLocation> {
        return userLocationRepository.updateLocation(id, coordinate)
    }

    override fun getUserLocation(id: String): Result<UserLocation> {
        return userLocationRepository.getUserLocation(id)
    }

    override fun findDriverByCoordinate(coordinate: Coordinate): Result<List<User>> {
        val userLocationList = userLocationRepository.findDriverByCoordinate(coordinate)
            .map {
                it.map {
                    userRepository.getUserById(it.id).getOrNull()
                }.filterNotNull()
                    .filter { it.role == RoleEnum.DRIVER }
                    .filter { it.extras.safeCastTo(DriverExtra::class.java).isActive }
            }
        return userLocationList
    }
}