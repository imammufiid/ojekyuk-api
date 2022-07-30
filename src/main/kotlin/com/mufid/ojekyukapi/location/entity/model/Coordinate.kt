package com.mufid.ojekyukapi.location.entity.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Coordinate(
    @JsonProperty("latitude")
    var latitude: Double = 0.0,
    @JsonProperty("longitude")
    var longitude: Double = 0.0,
)
