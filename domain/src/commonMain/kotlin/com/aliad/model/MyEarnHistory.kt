package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class MyEarnHistory(
    val amount : String,
    val cardType : String,
    val id : Int,
    val storyNameBn : String,
    val createAt : String,
    val views : String,
    val userId : String
)