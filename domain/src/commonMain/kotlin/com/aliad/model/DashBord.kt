package com.aliad.model

data class DashBord(
    val lisOfPopularStories : List<MyBookItem> = emptyList(),
    val lifOfAllStories : List<MyBookItem> = emptyList(),
    val listOfNewReleaseStories : List<MyBookItem> = emptyList()
)
