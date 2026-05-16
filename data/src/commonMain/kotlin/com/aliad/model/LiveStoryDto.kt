package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
@Immutable
@Serializable
data class LiveStoryDto(
    val current_page: Int,
    val last_page: Int,
    val liveStoriesList: List<BookItem> = emptyList(),
    val per_page: Int,
    val status: Boolean,
    val total_items: Int
)