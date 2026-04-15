package com.aliad.model

import kotlinx.serialization.Serializable


@Serializable
data class ForgotPasswordDto(
    val message_bn: String,
    val message_en: String,
    val resetData: ResetData
)
@Serializable
data class ResetData(
    val email: String,
    val token: String
)