package com.mufid.ojekyukapi.booking.service

import com.mufid.ojekyukapi.booking.entity.Booking
import com.mufid.ojekyukapi.location.entity.model.Coordinate

interface BookingService {
    suspend fun getCurrentBookingCustomer(customerId: String, status: Booking.BookingStatus): Result<Booking>
    suspend fun getBookingsCustomer(customerId: String): Result<List<Booking>>
    suspend fun createBookingFromCustomer(
        customerId: String,
        from: Coordinate,
        destination: Coordinate
    ): Result<Booking>

    suspend fun startBookingFromCustomer(bookingId: String, transType: Booking.TransType): Result<Booking>
    suspend fun cancelBookingFromCustomer(bookingId: String): Result<Booking>
    suspend fun acceptBookingFromDriver(bookingId: String, driverId: String): Result<Booking>
    suspend fun ongoingBookingFromDriver(bookingId: String): Result<Booking>
    suspend fun completeBookingFromDriver(bookingId: String): Result<Booking>
}