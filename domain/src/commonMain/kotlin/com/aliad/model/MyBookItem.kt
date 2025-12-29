package com.aliad.model

import kotlinx.serialization.Serializable


@Serializable
data class MyBookItem(
    val category_name: String,
    val category_name_bn: String,
    val created_at: String,
)
