package com.aliad.model

import androidx.compose.ui.graphics.vector.ImageVector

data class SnackBarDetails(
    var details: String? = null,
    val title: String? = null,
    val show : Boolean = false,
    val leftIcon : ImageVector? = null
)
