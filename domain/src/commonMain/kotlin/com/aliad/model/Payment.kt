package com.aliad.model

data class Payment(
    val amount : String,
    val cardType : String,
    val id : Int,
    val planName : String,
    val planNameBn : String,
    val transactionID : String,
    val userId : String,
    val userName : String
)
