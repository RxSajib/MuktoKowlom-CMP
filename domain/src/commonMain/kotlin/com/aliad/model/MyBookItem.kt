package com.aliad.model

import kotlinx.serialization.Serializable


@Serializable
data class MyBookItem(
    val category_name: String?= null,
    val category_name_bn: String ?= null,
    val created_at: String?= null,
)
