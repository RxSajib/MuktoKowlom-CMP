package com.aliad.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class User(
    val name : String?= null,
    val id : Int?= null,
    val email : String?= null,
    val isVerified : String?= null,
    val phone : String?= null,
    val phoneTwo : String?= null,
    val address : String?= null,
    val profileImage : String?= null,
    val accessToken : String?= null,
    val createAtDate : String?= null
)
