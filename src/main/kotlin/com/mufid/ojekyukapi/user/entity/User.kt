package com.mufid.ojekyukapi.user.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.extra.DriverExtra
import com.mufid.ojekyukapi.user.entity.extra.emptyExtra
import com.mufid.ojekyukapi.utils.RoleEnum
import java.util.UUID

data class User(
    @JsonProperty("id")
    var id: String = "",
    @JsonProperty("username")
    var username: String = "",
    @JsonProperty("first_name")
    var firstName: String = "",
    @JsonProperty("last_name")
    var lastName: String = "",
    @JsonProperty("phone_number")
    var phoneNumber: String = "",
    @JsonProperty("email")
    var email: String = "",
    @JsonProperty("password")
    var password: String = "",
    @JsonProperty("location")
    var location: Coordinate = Coordinate(),
    @JsonProperty("fcm_token")
    var fcmToken: String = "",
    @JsonProperty("role")
    var role: RoleEnum = RoleEnum.CUSTOMER,
    @JsonProperty("extras")
    var extras: Any = emptyExtra()
) {
    companion object {
        fun createNewCustomer(
            username: String,
            password: String,
            firstName: String,
            lastName: String,
            phoneNumber: String,
            email: String
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password,
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                email = email,
                role = RoleEnum.CUSTOMER
            )
        }

        fun createNewDriver(
            username: String,
            password: String,
            firstName: String,
            lastName: String,
            phoneNumber: String,
            email: String,
            extra: DriverExtra
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password,
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                email = email,
                role = RoleEnum.DRIVER,
                extras = extra
            )
        }
    }
}
