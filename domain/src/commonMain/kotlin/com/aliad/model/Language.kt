package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class Language(
    val text: String?= null,
    val code: String= "en",
    val symbol: String?= null,
    val description: String?= null
)
