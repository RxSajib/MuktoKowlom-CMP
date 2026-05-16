package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class CommentDto(
    val comment: String?= null,
    val created_at: String?= null,
    val id: Int?= null,
    val parent_id: String?= null,
    val rating: Int?= null,
    val story_id: Int?= null,
    val updated_at: String?= null,
    val user_id: Int?= null
)