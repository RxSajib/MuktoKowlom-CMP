package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MyCommentData(
    val comment: String,
    val rating: Int,
    val story_id: Int,
    val user_id: Int
)