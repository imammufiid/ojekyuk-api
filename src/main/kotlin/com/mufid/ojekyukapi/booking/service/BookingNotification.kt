package com.mufid.ojekyukapi.booking.service

import com.mufid.ojekyukapi.booking.entity.Booking
import com.mufid.ojekyukapi.messaging.MessagingComponent
import com.mufid.ojekyukapi.messaging.entity.FcmMessage
import com.mufid.ojekyukapi.user.service.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object BookingNotification {
    fun findDrivers(
        booking: Booking, userService: UserService, messagingComponent: MessagingComponent
    ) {
        object : CoroutineScope {
            override val coroutineContext: CoroutineContext
                get() = Dispatchers.IO
        }.launch {
            val coordinate = booking.routeLocation.from.coordinate
            userService.findDriverByCoordinate(coordinate)
                .getOrNull()
                .orEmpty()
                .forEach { driver ->
                    val messageData = FcmMessage.FcmMessageData(
                        type = FcmMessage.Type.BOOKING,
                        customerId = booking.customerId,
                        driverId = "",
                        bookingId = booking.id
                    )
                    messagingComponent.sendMessage(driver.fcmToken, messageData)
                }
        }
    }
}