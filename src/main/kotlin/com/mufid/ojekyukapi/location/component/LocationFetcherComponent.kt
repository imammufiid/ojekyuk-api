package com.mufid.ojekyukapi.location.component

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.location.entity.response.LocationHereResult
import com.mufid.ojekyukapi.location.entity.response.LocationRouteHereResult
import okhttp3.OkHttpClient
import okhttp3.Request
import org.bson.json.JsonParseException
import org.springframework.stereotype.Component

@Component
class LocationFetcherComponent {
    private val client = OkHttpClient()
    private inline fun <reified T> getHttp(url: String): Result<T> {
        return try {
            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            val body = response.body
            val bodyString = body?.string()
            if (response.isSuccessful) {
                val data = ObjectMapper().readValue(bodyString, T::class.java)
                Result.success(data)
            } else {
                val throwable = IllegalArgumentException(response.message)
                Result.failure(throwable)
            }
        } catch (e: JsonParseException) {
            Result.failure(e)
        } catch (e: InvalidDefinitionException) {
            Result.failure(e)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

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
        return getHttp(url)
    }

    companion object {
        const val SEARCH_LOC = "https://discover.search.hereapi.com/v1/discover?at={{coordinate}}&limit=2&q={{name}}&apiKey=y0_Zze93Wxgohuh2C6xOwq91DToP9mXAwWyXRhBgRdk"
        const val REVERSE_LOC = "https://revgeocode.search.hereapi.com/v1/revgeocode?at={{coordinate}}&lang=en-US&apiKey=y0_Zze93Wxgohuh2C6xOwq91DToP9mXAwWyXRhBgRdk"
        const val ROUTE_LOC = "https://router.hereapi.com/v8/routes?transportMode=scooter&origin={{coordinate_origin}}&destination={{coordinate_destination}}&return=polyline&apikey=y0_Zze93Wxgohuh2C6xOwq91DToP9mXAwWyXRhBgRdk"
    }

    object Key {
        const val COORDINATE = "{{coordinate}}"
        const val COORDINATE_ORIGIN = "{{coordinate_origin}}"
        const val COORDINATE_DESTINATION = "{{coordinate_destination}}"
        const val NAME = "{{name}}"
    }
}