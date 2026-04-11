package com.aliad.model.searchStory

import com.aliad.model.BookItem
import com.aliad.model.Link
import kotlinx.serialization.Serializable

@Serializable
data class SearchStoryDto(
    val current_page: Int?= null,
    val `data`: List<BookItem> = emptyList(),
    val first_page_url: String?= null,
    val from: Int?= null,
    val last_page: Int?= null,
    val last_page_url: String?= null,
    val links: List<Link> = emptyList(),
    val next_page_url: String?= null,
    val path: String?= null,
    val per_page: Int?= null,
    val prev_page_url: String?= null,
    val to: Int?= null,
    val total: Int?= null
)

