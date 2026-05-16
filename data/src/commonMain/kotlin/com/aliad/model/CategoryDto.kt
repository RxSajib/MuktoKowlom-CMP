package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
@Immutable
@Serializable
data class CategoryDto(
    val created_at: String,
    val id: Int,
    val image: String,
    val name: String,
    val name_bn: String,
    val status: String,
    val updated_at: String
)