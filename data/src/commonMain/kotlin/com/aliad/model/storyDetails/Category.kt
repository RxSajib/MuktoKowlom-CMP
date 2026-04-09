package com.aliad.model.storyDetails

import kotlinx.serialization.Serializable


@Serializable
data class Category(
    val created_at: String?= null,
    val id: Int?= null,
    val image: String?= null,
    val name: String?= null,
    val name_bn: String?= null,
    val status: String?= null,
    val updated_at: String?= null
)