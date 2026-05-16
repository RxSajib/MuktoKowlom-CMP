package com.aliad.model.subscription_history

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Payment(
    val amount: String,
    val card_type: String,
    val created_at: String,
    val expiry_date: String,
    val id: Int,
    val plan_name: String,
    val plan_name_bn: String,
    val transaction_id: String,
    val updated_at: String,
    val user_id: String,
    val user_name: String
)

@Immutable
@Serializable
data class SubscriptionHistoryDto(
    val current_page: Int,
    val payments: List<Payment>,
    val per_page: Int,
    val total: Int
)