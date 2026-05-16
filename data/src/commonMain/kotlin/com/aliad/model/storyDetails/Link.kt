package com.aliad.model.storyDetails

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)