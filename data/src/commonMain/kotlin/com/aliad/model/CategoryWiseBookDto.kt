package com.aliad.model

import kotlinx.serialization.Serializable



@Serializable
data class CategoryWiseBookDto(
    val current_page: Int,
    val `data`: List<BookItem>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<Link>,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_page_url: String,
    val to: Int,
    val total: Int
)

@Serializable
data class BookItem(
    val category_name: String,
    val category_name_bn: String,
    val created_at: String,
    val id: Int,
    val image: String,
    val image_thumb: String,
    val is_payable: String,
    val likeStories: List<LikeStory>,
    val publish_date: String,
    val rating: String,
    val story_bn: String,
    val story_file_bn: String,
    val summary_bn: String,
    val tags_bn: List<String>,
    val title_bn: String,
    val updated_at: String,
    val user_name: String,
    val views: String
)

@Serializable
data class LikeStory(
    val category_name: String,
    val category_name_bn: String,
    val id: Int,
    val image: String,
    val image_thumb: String,
    val is_payable: String,
    val rating: String,
    val story_bn: String,
    val summary_bn: String,
    val tags_bn: List<String>,
    val title_bn: String,
    val user_name: String,
    val views: String
)
@Serializable
data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)