package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class PrivacyPolicyDto(
    val created_at: String? = null,
    val description: String?= null,
    val description_bn: String?= null,
    val id: Int?= null,
    val title: String? = null,
    val title_bn: String?= null,
    val updated_at: String? = null
)