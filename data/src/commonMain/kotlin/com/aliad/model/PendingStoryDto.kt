package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class PendingStoryDto(
    val current_page: Int,
    val last_page: Int,
    val pendingStoriesList: List<BookItem>,
    val per_page: Int,
    val status: Boolean,
    val total_items: Int
)