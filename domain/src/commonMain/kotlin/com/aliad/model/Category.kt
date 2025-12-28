package com.aliad.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name : String = "",
    val name_bn : String = "",
    val id : Int = -1,
    val image : String = ""
){
    val completedImageUrl = "https://muktokowlom.com/$image"
}
