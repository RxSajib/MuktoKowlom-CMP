package com.aliad.model.earn_history

import com.aliad.model.Link
import kotlinx.serialization.Serializable

@Serializable
data class EarnHistoryDto(
    val current_page: Int,
    val `data`: List<EarnHistory>,
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
data class EarnHistory(
    val amount: String,
    val bank_txn: String,
    val card_type: String,
    val created_at: String,
    val id: Int,
    val mer_txnid: String,
    val pg_txnid: String,
    val story: Story,
    val story_id: String,
    val updated_at: String,
    val user_id: String,
    val views: String
)

@Serializable
data class Story(
    val id: Int,
    val title_bn: String
)
