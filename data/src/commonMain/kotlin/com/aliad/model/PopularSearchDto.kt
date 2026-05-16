package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class PopularSearchDto(
    val popularSearchStories: List<BookItem> = emptyList(),
    val status: Boolean = false
)