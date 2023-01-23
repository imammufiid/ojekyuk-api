package com.mufid.ojekyukapi.user.entity.extra

import com.fasterxml.jackson.annotation.JsonProperty

data class DriverExtra(
    @JsonProperty("vehicle_number")
    var vehicleNumber: String = "",
    @JsonProperty("is_active")
    var isActive: Boolean = false
) : Extra()
