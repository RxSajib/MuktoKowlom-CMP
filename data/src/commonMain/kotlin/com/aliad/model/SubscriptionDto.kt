package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionDto(
    val created_at: String?= null,
    val days: String?= null,
    val end_date: String?= null,
    val id: Int?= null,
    val name: String?= null,
    val name_bn: String?= null,
    val price: String?= null,
    val start_date: String?= null,
    val status: String?= null,
    val updated_at: String?= null
)