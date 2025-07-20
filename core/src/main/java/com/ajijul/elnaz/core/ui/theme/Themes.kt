package com.ajijul.elnaz.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ElnazInventoryTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        useDarkTheme -> darkColorSchemeFromXml()
        else -> lightColorSchemeFromXml()
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = parastooTypography(parastooFontFamily),
        content = content
    )
}

