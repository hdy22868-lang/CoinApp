package com.example.cryptocurrency.core.presentation.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimary,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = Color.White,
    onBackground = TextWhite
)

private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = MediumGray
)

@Composable
fun CryptocurrencyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}