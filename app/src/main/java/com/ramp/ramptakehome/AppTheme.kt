package com.ramp.ramptakehome

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme(
        primary = Color(0xFF6200EE),
        onPrimary = Color.White,
        primaryContainer = Color(0xFF3700B3),
        onPrimaryContainer = Color.White,
        inversePrimary = Color(0xFFBB86FC),
        secondary = Color(0xFF03DAC6),
        onSecondary = Color.Black,
        secondaryContainer = Color(0xFF018786),
        onSecondaryContainer = Color.White,
        tertiary = Color(0xFF03DAC6),
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFF018786),
        onTertiaryContainer = Color.White,
        background = Color(0xFFF6F6F6),
        onBackground = Color.Black,
        surface = Color(0xFFFFFFFF),
        onSurface = Color.Black,
        surfaceVariant = Color(0xFFF4F4F4),
        onSurfaceVariant = Color.Black,
        surfaceTint = Color(0xFF6200EE),
        inverseSurface = Color(0xFF121212),
        inverseOnSurface = Color.White,
        error = Color(0xFFB00020),
        onError = Color.White,
        errorContainer = Color(0xFFFDE7E7),
        onErrorContainer = Color.Black,
        outline = Color(0xFF737373),
        outlineVariant = Color(0xFFB6B6B6),
        scrim = Color(0x80000000)
    )


    MaterialTheme(
        colorScheme = colorScheme,
    ) {
        content()
    }
}