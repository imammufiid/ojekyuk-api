package com.mufid.ojekyukapi.utils

import com.mufid.ojekyukapi.location.entity.model.Route

object PriceCalculator {
    fun calculateRoute(routes: Route, fixPrice: Double = 15000.0): Double {
        val km = routes.distance.toDouble() / 1000.0
        return km * fixPrice
    }
}