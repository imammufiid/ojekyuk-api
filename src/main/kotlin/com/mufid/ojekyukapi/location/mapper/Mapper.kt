package com.mufid.ojekyukapi.location.mapper

import com.mufid.ojekyukapi.booking.entity.Booking
import com.mufid.ojekyukapi.booking.entity.BookingMinified
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

    fun mapRoutesHereToRoutes(locationRouteHereResult: LocationRouteHereResult): Pair<List<Coordinate>, Long> {
        val section = locationRouteHereResult.routes
            ?.firstOrNull()
            ?.sections
            ?.firstOrNull()

        val polylineString = section?.polyline.orEmpty()

        val coordinates = PolylineEncoderDecoder.decode(polylineString)
            .map { Coordinate(it.lat, it.lng) }
        val distance = section?.summary?.length ?: 0L
        return Pair(coordinates, distance)
    }

    fun mapBookingToMinified(booking: Booking): BookingMinified {
        return BookingMinified(
            id = booking.id,
            price = booking.price,
            transType = booking.transType,
            time = booking.time
        )
    }
}