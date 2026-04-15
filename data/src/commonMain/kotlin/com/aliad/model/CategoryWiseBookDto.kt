package com.aliad.model

import com.aliad.model.LoginDto
import kotlinx.serialization.Serializable



@Serializable
data class CategoryWiseBookDto(
    val current_page: Int?= null,
    val `data`: List<BookItem> = emptyList(),
    val first_page_url: String?= null,
    val from: Int? = null,
    val last_page: Int?= null,
    val last_page_url: String,
    val links: List<Link> = emptyList(),
    val next_page_url: String?= null,
    val path: String? = null,
    val per_page: Int ?= null,
    val prev_page_url: String?= null,
    val to: Int ?= null,
    val total: Int ?= null
)

@Serializable
data class BookItem(
    val category_name: String?= null,
    val category_name_bn: String ?= null,
    val created_at: String?= null,
    val id: Int,
    val image: String?= null,
    val image_thumb: String?= null,
    val is_payable: String?= null,
    val likeStories: List<LikeStory>  =emptyList(),
    val publish_date: String?= null,
    val rating: String ?= null,
    val story_bn: String?= null,
    val story_file_bn: String ? =null,
    val summary_bn: String?= null,
    val title_bn: String?= null,
    val summary : String?= null,
    val updated_at: String?= null,
    val user_name: String?= null,
    val views: String ?= null,
    val user: LoginDto?= null,
)

@Serializable
data class LikeStory(
    val category_name: String?= null,
    val category_name_bn: String ?= null,
    val id: Int,
    val image: String?= null,
    val image_thumb: String?= null,
    val is_payable: String?= null,
    val rating: String ?= null,
    val story_bn: String?= null,
    val summary_bn: String?= null,
    val title_bn: String ?= null,
    val user_name: String ?= null,
    val views: String ?= null
)
@Serializable
data class Link(
    val active: Boolean?= null,
    val label: String?= null,
    val url: String? =null
)