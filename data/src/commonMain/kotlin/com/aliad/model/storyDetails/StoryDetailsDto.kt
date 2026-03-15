package com.aliad.model.storyDetails

import kotlinx.serialization.Serializable


@Serializable
data class StoryDetailsDto(
    val allStoryComments: AllStoryComments,
    val comment_current_page: Int,
    val comment_last_page: Int,
    val comment_per_page: Int,
    val comment_total_items: Int,
    val current_page: Int,
    val `data`: DataX,
    val expiry_status: Int,
    val last_page: Int,
    val limitStoryComments: List<LimitStoryComment>,
    val per_page: Int,
    val status: Boolean,
    val total_items: Int
)