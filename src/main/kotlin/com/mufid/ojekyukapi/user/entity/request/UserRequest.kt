package com.mufid.ojekyukapi.user.entity.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.mufid.ojekyukapi.user.entity.User

data class UserRequest(

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
    fun mapToNewUser(): User {
        return User.createNewUser(username, password)
    }

    fun mapToNewCustomer(): User {
        return User.createNewCustomer(username, password, firstName, lastName, phoneNumber, email)
    }

    fun mapToNewDriver(): User {
        return User.createNewDriver(username, password, firstName, lastName, phoneNumber, email)
    }

    fun mapToCustomer(): User {
        return User.userCustomer(username, password, firstName, lastName, phoneNumber, email)
    }

    fun mapToDriver(): User {
        return User.userDriver(username, password, firstName, lastName, phoneNumber, email)
    }

}

