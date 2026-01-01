package com.aliad.model.dashboard


data class AllStory(
    val category_id: String,
    val category_name: String,
    val category_name_bn: String,
    val created_at: String,
    val id: Int,
    val image: String,
    val image_thumb: String,
    val is_payable: String,
    val publish_date: String,
    val rating: String,
    val story_bn: String,
    val story_file_bn: String,
    val summary_bn: String,
    val tags_bn: List<String>,
    val title_bn: String,
    val updated_at: String,
    val user_id: String,
    val user_name: String,
    val views: String
)