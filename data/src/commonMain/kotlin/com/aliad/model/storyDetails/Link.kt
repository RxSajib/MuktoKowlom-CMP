package com.aliad.model.storyDetails

import kotlinx.serialization.Serializable


@Serializable
data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)