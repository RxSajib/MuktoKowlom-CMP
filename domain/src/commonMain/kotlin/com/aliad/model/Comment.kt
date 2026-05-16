package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class Comment(
    val comment : String,
    val id : Int,
    val rating : Int
)
