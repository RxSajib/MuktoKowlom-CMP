package com.aliad.model.subscription_history

import kotlinx.serialization.Serializable


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