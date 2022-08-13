package com.mufid.ojekyukapi.user.entity.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.mufid.ojekyukapi.user.entity.User
import com.mufid.ojekyukapi.user.entity.extra.DriverExtra

data class DriverRegisterRequest(
    @JsonProperty("username")
    val username: String,

    @JsonProperty("password")
    val password: String,

    @JsonProperty("first_name")
    val firstName: String,

    @JsonProperty("last_name")
    val lastName: String,

    @JsonProperty("phone_number")
    val phoneNumber: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("vehicle_number")
    val vehicleNumber: String
) {
    fun mapToUser() : User {
        val userExtras = DriverExtra(vehicleNumber)
        return User.createNewDriver(username, password, firstName, lastName, phoneNumber, email, userExtras)
    }
}
