package com.aliad.muktokowlom.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.poppins_regular

@Composable
fun MuktoKowlomTheme(appContent: @Composable () -> Unit){



    val appThemes = isSystemInDarkTheme()
    val appColors = if (appThemes) darkColorScheme else lightColorScheme

    MaterialTheme(
        colorScheme = appColors,
        content = appContent,
        typography = getMaterialTypography()
    )
}