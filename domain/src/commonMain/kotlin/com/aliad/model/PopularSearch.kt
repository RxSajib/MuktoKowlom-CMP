package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class PopularSearch(
    val storyList : List<MyBookItem> = emptyList()
)
