package com.mufid.ojekyukapi.user.entity

import com.mufid.ojekyukapi.location.entity.model.Coordinate

data class UserLocation(
    var id: String,
    var coordinate: Coordinate
) {

    @Suppress("SuspiciousVarProperty")
    var lngLat: List<Double> = emptyList()
        get() = listOf(coordinate.longitude, coordinate.latitude)
}
