package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class StoryCountDto(
    val current_page: Int,
    val join_since: String,
    val last_page: Int,
    val liveStory: Int,
    val pendingStory: Int,
    val per_page: Int,
    val status: Boolean,
    val total_items: Int
)