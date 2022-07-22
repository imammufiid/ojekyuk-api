package com.mufid.ojekyukapi.location.service

import com.mufid.ojekyukapi.location.component.LocationFetcherComponent
import com.mufid.ojekyukapi.location.mapper.Mapper
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.location.entity.model.Location
import com.mufid.ojekyukapi.location.entity.model.Route
import com.mufid.ojekyukapi.utils.orThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationServiceImpl(
    @Autowired
    private val fetcher: LocationFetcherComponent
) : LocationService {
    override fun searchLocation(name: String, coordinate: Coordinate): Result<List<Location>> {
        return fetcher.searchLocation(name, coordinate).map {
            Mapper.mapSearchLocationHereToLocation(it)
        }
    }

    override fun reverseLocation(coordinate: Coordinate): Result<Location> {
        return fetcher.reverseLocation(coordinate).map {
            Mapper.mapSearchLocationHereToLocation(it).firstOrNull().orThrow("Location not found")
        }
    }

    override fun getRoutes(origin: Coordinate, destination: Coordinate): Result<Route> {
        return fetcher.getRouteLocation(origin, destination).map {
            val coordinate = Mapper.mapRoutesHereToRoutes(it)
            Route(coordinate)
        }
    }
}