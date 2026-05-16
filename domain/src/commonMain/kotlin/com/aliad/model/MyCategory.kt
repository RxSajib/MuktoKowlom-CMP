package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MyCategory(
    val name : String = "",
    val name_bn : String = "",
    val id : Int = -1,
    val image : String = ""
){
    val completedImageUrl = "https://muktokowlom.com/$image"
}
