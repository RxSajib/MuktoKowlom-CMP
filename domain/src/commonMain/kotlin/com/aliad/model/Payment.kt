package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Payment(
    val amount : String?= null,
    val cardType : String?= null,
    val id : Int?= null,
    val planName : String?= null,
    val planNameBn : String?= null,
    val transactionID : String?= null,
    val userId : String?= null,
    val userName : String?= null,
    val expiryDate : String?= null,
    val createdDate : String?= null
)
