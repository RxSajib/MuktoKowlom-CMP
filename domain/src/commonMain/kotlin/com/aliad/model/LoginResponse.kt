package com.aliad.model


data class User(
    val name : String,
    val id : Int,
    val email : String,
    val isVerified : Int,
    val phone : String,
    val phoneTwo : String,
    val address : String,
    val profileImage : String
){
    val completedProfileImage = profileImage
}
