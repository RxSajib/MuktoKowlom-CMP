package com.aliad.muktokowlom.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val darkColorScheme = darkColorScheme(
    surface = Black,
    primary = colorPrimaryDark,
    inverseSurface = colorSurfaceTintDark,
    onSurface = White,
    onPrimary = onPrimaryDark,
)

val lightColorScheme = lightColorScheme(
    surface = White,
    primary = colorPrimaryLight,
    inverseSurface = colorSurfaceTintLight,
    onSurface = Black,
    onPrimary = onPrimaryLight
)