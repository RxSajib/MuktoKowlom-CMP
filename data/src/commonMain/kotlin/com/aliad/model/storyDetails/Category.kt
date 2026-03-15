package com.aliad.model.storyDetails

import kotlinx.serialization.Serializable


@Serializable
data class Category(
    val created_at: String,
    val id: Int,
    val image: String,
    val name: String,
    val name_bn: String,
    val status: String,
    val updated_at: String
)