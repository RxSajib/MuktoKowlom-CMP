package com.aliad.model.earn_history

import androidx.compose.runtime.Immutable
import com.aliad.model.Link
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class EarnHistoryDto(
    val current_page: Int?= null,
    val `data`: List<EarnHistory> = emptyList(),
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

@Immutable
@Serializable
data class EarnHistory(
    val amount: String?= null,
    val bank_txn: String?= null,
    val card_type: String?= null,
    val created_at: String?= null,
    val id: Int?= null,
    val mer_txnid: String?= null,
    val pg_txnid: String?= null,
    val story: Story?= null,
    val story_id: String?= null,
    val updated_at: String?= null,
    val user_id: String?= null,
    val views: String?= null
)

@Immutable
@Serializable
data class Story(
    val id: Int,
    val title_bn: String
)
