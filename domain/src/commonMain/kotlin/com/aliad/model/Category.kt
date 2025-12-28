package com.aliad.model

data class Category(
    val name : String,
    val name_bn : String,
    val id : Int,
    val image : String
){
    val completedImageUrl = "https://muktokowlom.com/$image"
}
