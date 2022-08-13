package com.mufid.ojekyukapi.user.entity.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.mufid.ojekyukapi.user.entity.User

data class CustomerRegisterRequest(
    @JsonProperty("username")
    var username: String,

    @JsonProperty("password")
    var password: String,

    @JsonProperty("first_name")
    var firstName: String,

    @JsonProperty("last_name")
    var lastName: String,

    @JsonProperty("phone_number")
    var phoneNumber: String,

    @JsonProperty("email")
    var email: String
) {
    fun mapToUser() : User {
        return User.createNewCustomer(username, password, firstName, lastName, phoneNumber, email)
    }
}
