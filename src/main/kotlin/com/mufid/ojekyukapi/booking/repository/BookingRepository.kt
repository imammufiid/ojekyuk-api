package com.mufid.ojekyukapi.booking.repository

import com.mufid.ojekyukapi.booking.entity.Booking

interface BookingRepository {
    fun insertBooking(booking: Booking): Result<Booking>
    fun getBookingById(bookingId: String): Result<Booking>
    fun getBookingByCustomerId(customerId: String): Result<Booking>
    fun getBookingsByCustomerId(customerId: String): Result<List<Booking>>
    fun getBookingByCustomerIdAndStatus(customerId: String, status: Booking.BookingStatus): Result<Booking>
    fun startBooking(bookingId: String, transType: Booking.TransType, driverId: String, price: Double): Result<Booking>
    fun updateBookingStatus(bookingId: String, bookingStatus: Booking.BookingStatus, driverId: String): Result<Booking>
}