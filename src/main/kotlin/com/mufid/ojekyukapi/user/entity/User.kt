package com.mufid.ojekyukapi.user.entity

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("role")
    var role: Int = RoleEnum.CUSTOMER.id
) {
    companion object {
        fun createNewUser(
            username: String,
            password: String,
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password
            )
        }

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
                role = RoleEnum.CUSTOMER.id
            )
        }

        fun createNewDriver(
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
                role = RoleEnum.DRIVER.id
            )
        }
    }
}
