package com.mufid.ojekyukapi.user.entity.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UserLoginRequest(
    @JsonProperty("username")
    var username: String,
    @JsonProperty("password")
    var password: String
)
