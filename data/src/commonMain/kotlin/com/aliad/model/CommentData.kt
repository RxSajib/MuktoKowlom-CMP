package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class CommentData(
    val comment: String,
    val rating: Int,
    val story_id: Int,
    val user_id: Int
)