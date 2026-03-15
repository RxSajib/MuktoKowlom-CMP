package com.aliad.muktokowlom.data.enum

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PaymentType {
    Nagad,
    @SerialName("bKash-bKash")
    bKash,
    UPAY

}