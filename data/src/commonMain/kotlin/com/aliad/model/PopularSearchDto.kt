package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class PopularSearchDto(
    val popularSearchStories: List<BookItem> = emptyList(),
    val status: Boolean = false
)