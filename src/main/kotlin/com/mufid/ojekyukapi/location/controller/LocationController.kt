package com.mufid.ojekyukapi.location.controller

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.location.service.LocationService
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.location.entity.model.Location
import com.mufid.ojekyukapi.location.entity.model.Route
import com.mufid.ojekyukapi.utils.asResponse
import com.mufid.ojekyukapi.utils.coordinateStringToData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/location")
class LocationController {
    @Autowired
    private lateinit var locationService: LocationService

    @GetMapping("/search")
    fun searchLocation(
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "coordinate") coordinate: String
    ): BaseResponse<List<Location>> {
        val lat = coordinate.split(",")[0].toDoubleOrNull() ?: 0.0
        val lng = coordinate.split(",")[1].toDoubleOrNull() ?: 0.0
        return locationService.searchLocation(name, Coordinate(lat, lng)).asResponse()
    }

    @GetMapping("/reverse")
    fun reverseLocation(
        @RequestParam(value = "coordinate") coordinate: String
    ): BaseResponse<Location> {
        val lat = coordinate.split(",")[0].toDoubleOrNull() ?: 0.0
        val lng = coordinate.split(",")[1].toDoubleOrNull() ?: 0.0
        println(locationService.reverseLocation(Coordinate(lat, lng)).asResponse())
        return locationService.reverseLocation(Coordinate(lat, lng)).asResponse()
    }

    @GetMapping("/route")
    fun routeLocation(
        @RequestParam(value = "origin") origin: String,
        @RequestParam(value = "destination") destination: String
    ): BaseResponse<Route> {
        val coordinateOrigin = origin.coordinateStringToData()
        val coordinateDes = destination.coordinateStringToData()
        return locationService.getRoutes(coordinateOrigin, coordinateDes).asResponse()
    }
}