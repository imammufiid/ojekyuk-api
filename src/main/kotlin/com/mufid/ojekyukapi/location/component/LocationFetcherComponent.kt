package com.mufid.ojekyukapi.location.component

import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.location.entity.response.LocationHereResult
import com.mufid.ojekyukapi.location.entity.response.LocationRouteHereResult
import com.mufid.ojekyukapi.utils.BaseComponent
import org.springframework.stereotype.Component

@Component
class LocationFetcherComponent : BaseComponent() {

    fun searchLocation(name: String, coordinate: Coordinate): Result<LocationHereResult> {
        val coordinateString = "${coordinate.latitude},${coordinate.longitude}"
        val url = SEARCH_LOC
            .replace(Key.COORDINATE, coordinateString)
            .replace(Key.NAME, name)
        return getHttp(url)
    }

    fun reverseLocation(coordinate: Coordinate): Result<LocationHereResult> {
        val coordinateString = "${coordinate.latitude},${coordinate.longitude}"
        val url = REVERSE_LOC
            .replace(Key.COORDINATE, coordinateString)
        return getHttp(url)
    }

    fun getRouteLocation(coordinateOrigin: Coordinate, coordinateDestination: Coordinate): Result<LocationRouteHereResult> {
        val coordinateString = "${coordinateOrigin.latitude},${coordinateOrigin.longitude}"
        val coordinateDesString = "${coordinateDestination.latitude},${coordinateDestination.longitude}"
        val url = ROUTE_LOC
            .replace(Key.COORDINATE_ORIGIN, coordinateString)
            .replace(Key.COORDINATE_DESTINATION, coordinateDesString)
            .replace(Key.TRANSPORT_TYPE, "car")
        return getHttp(url)
    }

    companion object {
        const val SEARCH_LOC = "https://discover.search.hereapi.com/v1/discover?at={{coordinate}}&limit=2&q={{name}}&apiKey=y0_Zze93Wxgohuh2C6xOwq91DToP9mXAwWyXRhBgRdk"
        const val REVERSE_LOC = "https://revgeocode.search.hereapi.com/v1/revgeocode?at={{coordinate}}&lang=en-US&apiKey=y0_Zze93Wxgohuh2C6xOwq91DToP9mXAwWyXRhBgRdk"
        const val ROUTE_LOC = "https://router.hereapi.com/v8/routes?transportMode={{transport_type}}&origin={{coordinate_origin}}&destination={{coordinate_destination}}&return=polyline&apikey=y0_Zze93Wxgohuh2C6xOwq91DToP9mXAwWyXRhBgRdk"
    }

    object Key {
        const val COORDINATE = "{{coordinate}}"
        const val COORDINATE_ORIGIN = "{{coordinate_origin}}"
        const val COORDINATE_DESTINATION = "{{coordinate_destination}}"
        const val TRANSPORT_TYPE = "{{transport_type}}"
        const val NAME = "{{name}}"
    }
}