package com.aliad.model

import kotlinx.serialization.Serializable


@Serializable
data class MyBookItem(
    val category_name: String? = null,
    val category_name_bn: String? = null,
    val created_at: String? = null,
    val titleBn: String? = null,
    val titleEn: String? = null,
    val image : String ?= null,
    val rating : String ?= null,
    val authorName : String?= null,
    val summaryBn : String? = null,
    val summaryEn : String?= null
){
    val completedImageUri = "https://muktokowlom.com/$image"
    val ratingToInt = convertStringToInt(rating?:"0")
}

fun convertStringToInt(input: String): Int? {
    val doubleValue: Double? = input.toDoubleOrNull()
    return doubleValue?.toInt()
}
