package com.mufid.ojekyukapi.booking.service

import com.mufid.ojekyukapi.booking.entity.Booking
import com.mufid.ojekyukapi.booking.repository.BookingRepository
import com.mufid.ojekyukapi.location.entity.model.Coordinate
import com.mufid.ojekyukapi.location.service.LocationService
import com.mufid.ojekyukapi.messaging.MessagingComponent
import com.mufid.ojekyukapi.messaging.entity.FcmMessage
import com.mufid.ojekyukapi.user.service.UserService
import com.mufid.ojekyukapi.utils.PriceCalculator
import com.mufid.ojekyukapi.utils.handler.OjekyukException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BookingServiceImpl(
    @Autowired
    private val bookingRepository: BookingRepository,
    @Autowired
    private val locationService: LocationService,
    @Autowired
    private val userService: UserService,
    @Autowired
    private val messagingComponent: MessagingComponent
) : BookingService {
    override suspend fun getCurrentBookingCustomer(customerId: String, status: Booking.BookingStatus): Result<Booking> {
        return bookingRepository.getBookingByCustomerIdAndStatus(customerId, status)
    }

    override suspend fun getBookingsCustomer(customerId: String): Result<List<Booking>> {
        return bookingRepository.getBookingsByCustomerId(customerId)
    }

    override suspend fun createBookingFromCustomer(
        customerId: String,
        from: Coordinate,
        destination: Coordinate
    ): Result<Booking> {
        if (customerId.isEmpty()) throw OjekyukException("Customer id is empty")
        val routes = locationService.getRoutes(from, destination).getOrThrow()
        val fromLocation = locationService.reserveLocation(from).getOrThrow()
        val destLocation = locationService.reserveLocation(destination).getOrThrow()
        val price = PriceCalculator.calculateRoute(routes)

        val routeLocation = Booking.RouteLocation(
            from = fromLocation,
            destination = destLocation,
            routes = routes
        )

        val booking = Booking(
            id = UUID.randomUUID().toString(),
            customerId = customerId,
            routeLocation = routeLocation,
            price = price,
            status = Booking.BookingStatus.READY
        )
        return bookingRepository.insertBooking(booking)
    }

    override suspend fun startBookingFromCustomer(bookingId: String, transType: Booking.TransType): Result<Booking> {
        val currentBooking = bookingRepository.getBookingById(bookingId).getOrThrow()
        val currentRoutesLocation = currentBooking.routeLocation
        val currentRoutes = currentRoutesLocation.routes
        val newPrice = PriceCalculator.calculateRoute(currentRoutes, 22000.0)
        bookingRepository.startBooking(
            bookingId, transType, currentBooking.driverId, newPrice
        )

        BookingNotification.findDrivers(
            booking = currentBooking,
            userService = userService,
            messagingComponent = messagingComponent
        )

        notifyToUser(currentBooking.customerId, currentBooking.driverId, currentBooking)
        return bookingRepository.getBookingById(bookingId)

    }

    override suspend fun cancelBookingFromCustomer(bookingId: String): Result<Booking> {
        val currentBooking = bookingRepository.getBookingById(bookingId).getOrThrow()
        bookingRepository.updateBookingStatus(
            bookingId,
            bookingStatus = Booking.BookingStatus.CANCELED,
            driverId = currentBooking.driverId
        )
        notifyToUser(currentBooking.customerId, currentBooking.driverId, currentBooking)
        return bookingRepository.getBookingById(bookingId)
    }

    override suspend fun acceptBookingFromDriver(bookingId: String, driverId: String): Result<Booking> {
        val currentBooking = bookingRepository.getBookingById(bookingId).getOrThrow()
        bookingRepository.updateBookingStatus(
            bookingId,
            bookingStatus = Booking.BookingStatus.ACCEPTED,
            driverId = currentBooking.driverId
        )
        notifyToUser(currentBooking.customerId, currentBooking.driverId, currentBooking)
        return bookingRepository.getBookingById(bookingId)
    }

    override suspend fun ongoingBookingFromDriver(bookingId: String): Result<Booking> {
        val currentBooking = bookingRepository.getBookingById(bookingId).getOrThrow()
        bookingRepository.updateBookingStatus(
            bookingId,
            bookingStatus = Booking.BookingStatus.ONGOING,
            driverId = currentBooking.driverId
        )
        notifyToUser(currentBooking.customerId, currentBooking.driverId, currentBooking)
        return bookingRepository.getBookingById(bookingId)
    }

    override suspend fun completeBookingFromDriver(bookingId: String): Result<Booking> {
        val currentBooking = bookingRepository.getBookingById(bookingId).getOrThrow()
        bookingRepository.updateBookingStatus(
            bookingId,
            bookingStatus = Booking.BookingStatus.DONE,
            driverId = currentBooking.driverId
        )
        notifyToUser(currentBooking.customerId, currentBooking.driverId, currentBooking)
        return bookingRepository.getBookingById(bookingId)
    }

    private suspend fun notifyToUser(customerId: String, driverId: String, booking: Booking) {
        val customer = userService.getUserById(customerId).getOrNull()
        val driver = userService.getUserById(driverId).getOrNull()

        val messageData = FcmMessage.FcmMessageData(
            type = FcmMessage.Type.BOOKING,
            customerId = customerId,
            driverId = driverId,
            bookingId = booking.id
        )

        if (customer != null) {
            messagingComponent.sendMessage(customer.fcmToken, messageData)
        }

        if (driver != null) {
            messagingComponent.sendMessage(driver.fcmToken, messageData)
        }
    }
}