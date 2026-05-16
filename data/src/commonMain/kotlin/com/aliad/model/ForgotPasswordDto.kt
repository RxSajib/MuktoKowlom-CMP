package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class ForgotPasswordDto(
    val message_bn: String,
    val message_en: String,
    val resetData: ResetData
)
@Immutable
@Serializable
data class ResetData(
    val email: String,
    val token: String
)