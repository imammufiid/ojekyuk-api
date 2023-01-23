package com.mufid.ojekyukapi.user.repository

import com.mongodb.client.model.geojson.Point
import com.mongodb.client.model.geojson.Position
import com.mufid.ojekyukapi.database.DatabaseComponent
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.user.entity.UserLocation
import com.mufid.ojekyukapi.utils.extension.collection
import com.mufid.ojekyukapi.utils.toResult
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserLocationRepositoryImpl(
    @Autowired
    private val databaseComponent: DatabaseComponent
) : UserLocationRepository {

    private val dbUserLocation
        get() = databaseComponent.collection<UserLocation>()

    override fun updateLocation(userId: String, coordinate: Coordinate): Result<UserLocation> {
        val isExistUserLocation = getUserLocation(userId).isSuccess
        if (isExistUserLocation) {
            dbUserLocation.updateOne(
                UserLocation::id eq userId,
                UserLocation::coordinate setTo coordinate
            )
        } else {
            dbUserLocation.insertOne(UserLocation(userId, coordinate))
        }
        return getUserLocation(userId)
    }

    override fun getUserLocation(userId: String): Result<UserLocation> {
        return dbUserLocation.findOne(UserLocation::id eq userId).toResult()
    }

    override fun findDriverByCoordinate(coordinate: Coordinate): Result<List<UserLocation>> {
        val point = Point(Position(coordinate.longitude, coordinate.latitude))
        dbUserLocation.createIndex(geo2dsphere(UserLocation::lngLat))
        return dbUserLocation.find(
            UserLocation::lngLat.nearSphere(point, 2000.0)
        ).toList().toResult()
    }
}