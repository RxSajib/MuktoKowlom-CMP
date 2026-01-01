package com.aliad.muktokowlom.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val darkColorScheme = darkColorScheme(
    surface = Black,
    primary = colorPrimaryDark,
    inverseSurface = colorSurfaceTintDark,
    onSurface = White
)

val lightColorScheme = lightColorScheme(
    surface = White,
    primary = colorPrimaryLight,
    inverseSurface = colorSurfaceTintLight,
    onSurface = Black
)