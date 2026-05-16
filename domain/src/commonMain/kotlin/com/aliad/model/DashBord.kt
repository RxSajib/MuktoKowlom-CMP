package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class DashBord(
    val lisOfPopularStories : List<MyBookItem> = emptyList(),
    val lifOfAllStories : List<MyBookItem> = emptyList(),
    val listOfNewReleaseStories : List<MyBookItem> = emptyList()
)
