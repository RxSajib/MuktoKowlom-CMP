package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class ResetPasswordResponse(

    val messageEn : String,
    val messageBn : String,
    val emailAddress : String,
    val token : String
)
