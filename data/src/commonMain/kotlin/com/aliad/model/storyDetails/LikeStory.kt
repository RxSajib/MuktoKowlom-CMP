package com.aliad.model.storyDetails

import kotlinx.serialization.Serializable


@Serializable
data class LikeStory(
    val category: Category,
    val category_id: String,
    val created_at: String,
    val id: Int,
    val image: String,
    val image_thumb: String,
    val is_payable: String,
    val publish_date: String,
    val rating: String,
    val status: String,
    val story: String,
    val story_bn: String,
    val story_file: String,
    val story_file_bn: String,
    val summary: String,
    val summary_bn: String,
    val tags: String,
    val tags_bn: List<String>,
    val title: String,
    val title_bn: String,
    val updated_at: String,
    val user: User,
    val user_id: String,
    val views: String
)