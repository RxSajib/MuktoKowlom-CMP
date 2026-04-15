package com.aliad.model

data class ResetPasswordResponse(

    val messageEn : String,
    val messageBn : String,
    val emailAddress : String,
    val token : String
)
