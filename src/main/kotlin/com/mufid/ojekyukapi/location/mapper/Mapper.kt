package com.mufid.ojekyukapi.location.mapper

import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.location.entity.model.Location
import com.mufid.ojekyukapi.location.entity.response.LocationHereResult
import com.mufid.ojekyukapi.location.entity.response.LocationRouteHereResult
import com.mufid.ojekyukapi.location.utils.PolylineEncoderDecoder

object Mapper {
    fun mapSearchLocationHereToLocation(locationHereResult: LocationHereResult): List<Location> {
        return locationHereResult.items?.map {
        val address = Location.Address(
            city = it?.address?.city.orEmpty(),
            country = it?.address?.countryName.orEmpty(),
            district = it?.address?.district.orEmpty(),
        )
            Location(
                name = it?.title.orEmpty(),
                address = address,
                coordinate = Coordinate(it?.position?.lat ?: 0.0, it?.position?.lng ?: 0.0)
            )
        }.orEmpty()
    }

    fun mapRoutesHereToRoutes(locationRouteHereResult: LocationRouteHereResult): List<Coordinate> {
        val polylineString = locationRouteHereResult.routes?.firstOrNull()?.sections?.firstOrNull()?.polyline
        return PolylineEncoderDecoder.decode(polylineString)
            .map { Coordinate(it.lat, it.lng) }
    }
}