package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class StoryCount(
    val liveStoryCount : Int = 0,
    val pendingStoryCount : Int = 0,
    val joinSince : String = ""
)
