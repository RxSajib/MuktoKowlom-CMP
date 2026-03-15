package com.aliad.model.storyDetails

import kotlinx.serialization.Serializable


@Serializable
data class Data(
    val comment: String,
    val created_at: String,
    val id: Int,
    val parent_id: String,
    val rating: String,
    val replies: List<String>,
    val story_id: String,
    val updated_at: String,
    val user_id: String
)