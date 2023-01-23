package com.mufid.ojekyukapi.booking.controller

import com.mufid.ojekyukapi.BaseResponse
import com.mufid.ojekyukapi.booking.entity.Booking
import com.mufid.ojekyukapi.booking.entity.BookingMinified
import com.mufid.ojekyukapi.booking.service.BookingService
import com.mufid.ojekyukapi.location.mapper.Mapper
import com.mufid.ojekyukapi.utils.asResponse
import com.mufid.ojekyukapi.utils.findUserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/booking")
class BookingController {
    @Autowired
    private lateinit var bookingService: BookingService

    @GetMapping
    suspend fun getCurrentBookingCustomer(
        @RequestParam(value = "status") status: Booking.BookingStatus
    ): BaseResponse<Booking> {
        return bookingService.getCurrentBookingCustomer(findUserId().orEmpty(), status).asResponse()
    }

    @GetMapping("/activity")
    suspend fun getActivityBookingCustomer(): BaseResponse<List<BookingMinified>> {
        return bookingService.getBookingsCustomer(findUserId().orEmpty()).map {
            it.map { booking -> Mapper.mapBookingToMinified(booking) }
        }.asResponse()
    }

    @PostMapping("/request")
    suspend fun postBookingRequestCustomer(
        @RequestParam(value = "booking_id", required = true) bookingId: String,
        @RequestParam(value = "trans_type", required = true) transType: Booking.TransType,
    ): BaseResponse<Booking> {
        return bookingService.startBookingFromCustomer(bookingId, transType).asResponse()
    }

    @PostMapping("/cancel")
    suspend fun cancelBookingCustomer(
        @RequestParam(value = "booking_id", required = true) bookingId: String
    ): BaseResponse<Booking> {
        return bookingService.cancelBookingFromCustomer(bookingId).asResponse()
    }
}