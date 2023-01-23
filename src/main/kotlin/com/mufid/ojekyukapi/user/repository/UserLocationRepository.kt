package com.mufid.ojekyukapi.user.repository

import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.UserLocation

interface UserLocationRepository {

    fun updateLocation(userId: String, coordinate: Coordinate): Result<UserLocation>
    fun getUserLocation(userId: String): Result<UserLocation>
    fun findDriverByCoordinate(coordinate: Coordinate): Result<List<UserLocation>>
}