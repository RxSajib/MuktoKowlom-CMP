package com.aliad.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class SnackBarDetails(
    var details: String? = null,
    val title: String? = null,
    val show : Boolean = false,
    val isSuccess : Boolean = true,
    val leftIcon : ImageVector? = null
)
