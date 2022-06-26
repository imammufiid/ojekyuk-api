package com.mufid.ojekyukapi.user.entity

data class UserRequest(
    var username: String,
    var password: String
) {
    fun mapToNewUser(): User {
        return User.createNewUser(username, password)
    }

}

