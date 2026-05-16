package com.aliad.model

import androidx.compose.runtime.Immutable

@Immutable
data class PrivacyPolicy(
    val description: String = "",
    val descriptionBn: String = "",
    val id: Int?= null,
    val title: String = "",
    val titleBn: String = "",
)
