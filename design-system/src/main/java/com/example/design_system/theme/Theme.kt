package com.example.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorPalette = lightColorScheme(
    primary = Purple,
    secondary = DarkGray,
    onSurface = BlackText,
    onBackground = GrayText,
)

val DarkColorPalette = darkColorScheme(
    primary = Purple,
    secondary = DarkGray,
    onSurface = WhiteText,
    onBackground = GrayText,
)

@Composable
fun BaseAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val baseColors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = baseColors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}