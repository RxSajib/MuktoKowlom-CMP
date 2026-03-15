package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class Payment(
    val amount : String,
    val cardType : String,
    val id : Int,
    val planName : String,
    val planNameBn : String,
    val transactionID : String,
    val userId : String,
    val userName : String,
    val expiryDate : String,
    val createdDate : String
)
