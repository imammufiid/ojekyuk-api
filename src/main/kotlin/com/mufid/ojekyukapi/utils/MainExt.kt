package com.mufid.ojekyukapi.utils

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.utils.handler.OjekyukException

inline fun <reified T> T?.orThrow(
    message: String = "${T::class.simpleName} is null"
): T {
    return this ?: throw OjekyukException(message)
}

inline fun <reified T> T?.toResult(
    message: String = "${T::class.simpleName} is null"
): Result<T> {
    return if (this != null) {
        Result.success(this)
    } else {
        Result.failure(OjekyukException(message))
    }
}

fun <T> Result<T>.asResponse(): BaseResponse<T> {
    return if (this.isFailure) {
        throw OjekyukException(this.exceptionOrNull()?.message ?: "Failure")
    } else {
        BaseResponse.success(this.getOrNull())
    }
}

fun String.coordinateStringToData(): Coordinate {
    val coordinateStrings = split(",")
    val lat = coordinateStrings[0].toDoubleOrNull() ?: 0.0
    val lon = coordinateStrings[1].toDoubleOrNull() ?: 0.0
    return Coordinate(lat, lon)
}