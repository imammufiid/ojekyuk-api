package com.mufid.ojekyukapi.utils.extension

import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.utils.GeoUtils
import kotlin.math.roundToLong

fun Coordinate.distanceFrom(coordinate: Coordinate): Double {
    return GeoUtils.computeDistance(this, coordinate).roundToLong().toDouble()
}

fun Collection<Coordinate>.computeDistance(): Double {
    var resultMeter = 0.0
    forEachIndexed { index, coordinate ->
        try {
            val current = coordinate.distanceFrom(this.toList()[index+1])
            resultMeter += current
        } catch (_: IndexOutOfBoundsException) { }
    }
    return resultMeter.roundToLong().toDouble()
}