package com.aliad.model.subscription_history

import kotlinx.serialization.Serializable


@Serializable
data class SubscriptionHistoryDto(
    val current_page: Int,
    val payments: List<Payment>,
    val per_page: Int,
    val total: Int
)