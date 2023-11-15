package com.quraanali.trendingmovies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkColorPalette = darkColorScheme(
    primary = DarkPrimaryColor,
    secondary = DarkPrimaryColor,
    surface = DarkSurface,
    onSurface = DarkTextPrimary
)

private val lightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryColor,
    surface = Surface,
    onSurface = TextPrimary,
)

@Composable
fun TrendingMoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = /*if (darkTheme) darkColorPalette else */ lightColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes,
        content = content
    )
}
