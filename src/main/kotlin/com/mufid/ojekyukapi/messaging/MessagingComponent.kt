package com.mufid.ojekyukapi.messaging

import com.mufid.ojekyukapi.messaging.entity.FcmMessage
import com.mufid.ojekyukapi.utils.BaseComponent
import com.mufid.ojekyukapi.utils.extension.toJson
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.stereotype.Component

@Component
class MessagingComponent : BaseComponent() {
    val dummy = """
        {"data":{"type":"BOOKING","customerId":"175f27c1-76b5-468b-8362-a93cb07276af","driverId":"","bookingId":"48af43fe-7341-46bb-b799-4a0c66150024"},"token":"dofpd8h3Q-OAGKDrFMgYLQ:APA91bF-n9DK7DcxKx9DryyMQefzlQ5UHSAZiiGsCPvE49BVCJ8Dxlvc9tTvhRcChTYUGg9_qfKma8KlEICB2pHscSm91FgcG1k3ahhAMLQIJpSHAIXKfyI4TwZWMnYrH6A7r9Q5kfu3"}
    """.trimIndent()

    suspend fun sendMessage(token: String, fcmMessageData: FcmMessage.FcmMessageData): Result<Any> {
        val fcmMessage = FcmMessage(token, fcmMessageData)
        val requestBody = fcmMessage.toJson().toRequestBody("application/json; charset=utf-8".toMediaType())

        val url = "https://fcm.googleapis.com/fcm/send"
        val headers = mapOf(
            "Authorization" to "key=$SERVER_KEY",
            "Content-Type" to "application/json"
        ).toHeaders()

        return postHttp(
            url = url, requestBody = requestBody, header = headers
        )
    }

    companion object {
        private const val SERVER_KEY =
            "AAAA6_zvnHI:APA91bF-VGB3iJWSxPycEOKxzEHKsXDjGABC0uL_37lQKQcRKolPnehjQETDDNpHbCk-Rb6TtfG2NR7PTgt0ku7ZhqpRdMsNKnGyEXHxWkI-uKE4rBc4YTQ_sSs84_uv-2yQHFVi6Qpa"
    }
}