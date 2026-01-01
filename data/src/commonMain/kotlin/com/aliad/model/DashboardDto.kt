package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class DashboardDto(
    val allStories: List<BookItem> = emptyList(),
    val mostPopularStories: List<BookItem> = emptyList(),
    val newReleaseStories: List<BookItem> = emptyList(),
    val status: Boolean = false
)