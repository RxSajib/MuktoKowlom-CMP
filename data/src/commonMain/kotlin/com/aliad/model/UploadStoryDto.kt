package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class UploadStoryDto(
    val category_id: String?= null,
    val created_at: String?= null,
    val id: Int?= null,
    val image: String?= null,
    val image_thumb: String?= null,
    val is_payable: String?= null,
    val publish_date: String?= null,
    val story_bn: String?= null,
    val story_file_bn: String?= null,
    val summary_bn: String?= null,
    val tags_bn: String?= null,
    val title_bn: String?= null,
    val updated_at: String?= null,
    val user_id: Int?= null
)