package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class MyCommentData(
    val comment: String,
    val rating: Int,
    val story_id: Int,
    val user_id: Int
)