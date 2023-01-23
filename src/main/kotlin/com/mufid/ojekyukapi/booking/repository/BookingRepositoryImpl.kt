package com.mufid.ojekyukapi.booking.repository

import com.mufid.ojekyukapi.booking.entity.Booking
import com.mufid.ojekyukapi.database.DatabaseComponent
import com.mufid.ojekyukapi.utils.extension.collection
import com.mufid.ojekyukapi.utils.handler.OjekyukException
import com.mufid.ojekyukapi.utils.toResult
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class BookingRepositoryImpl(
    @Autowired
    private val databaseComponent: DatabaseComponent
) : BookingRepository {
    private val dbBooking
        get() = databaseComponent.collection<Booking>()

    override fun insertBooking(booking: Booking): Result<Booking> {
        val existingReadyBook =
            getBookingByCustomerIdAndStatus(booking.customerId, Booking.BookingStatus.READY).getOrNull()
        return if (existingReadyBook != null) {
            throw OjekyukException("Transaction is exist")
        } else {
            dbBooking.insertOne(booking)
            getBookingById(booking.id)
        }
    }

    override fun getBookingById(bookingId: String): Result<Booking> {
        return dbBooking.findOne(Booking::id eq bookingId).toResult("Transaction not found")
    }

    override fun getBookingByCustomerId(customerId: String): Result<Booking> {
        return dbBooking.findOne(Booking::customerId eq customerId).toResult("Transaction not found")
    }

    override fun getBookingsByCustomerId(customerId: String): Result<List<Booking>> {
        return dbBooking.find(Booking::customerId eq customerId).toList().toResult("Transaction not found")
    }

    override fun getBookingByCustomerIdAndStatus(customerId: String, status: Booking.BookingStatus): Result<Booking> {
        return dbBooking.findOne(
            Booking::customerId eq customerId,
            Booking::status eq status
        ).toResult("Transaction not found")
    }

    override fun startBooking(
        bookingId: String,
        transType: Booking.TransType,
        driverId: String,
        price: Double
    ): Result<Booking> {
        dbBooking.updateOne(
            Booking::id eq bookingId,
            Booking::transType setTo transType,
            Booking::driverId setTo driverId,
            Booking::price setTo price,
            Booking::status setTo Booking.BookingStatus.REQUEST,
        ).toResult()
        return getBookingById(bookingId)
    }

    override fun updateBookingStatus(
        bookingId: String,
        bookingStatus: Booking.BookingStatus,
        driverId: String
    ): Result<Booking> {
        dbBooking.updateOne(
            Booking::id eq bookingId,
            Booking::status setTo bookingStatus,
            Booking::driverId setTo driverId
        ).toResult()
        return getBookingById(bookingId)
    }
}