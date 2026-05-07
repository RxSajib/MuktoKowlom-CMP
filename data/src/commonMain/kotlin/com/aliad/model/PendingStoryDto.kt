package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class PendingStoryDto(
    val current_page: Int,
    val last_page: Int,
    val pendingStoriesList: List<BookItem>,
    val per_page: Int,
    val status: Boolean,
    val total_items: Int
)