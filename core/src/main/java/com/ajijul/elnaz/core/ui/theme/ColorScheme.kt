package com.ajijul.elnaz.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE), // Purple for primary actions
    onPrimary = Color.White, // White text/icon on primary
    primaryContainer = Color(0xFFEDE7F6), // Light purple for containers
    onPrimaryContainer = Color.Black, // Black text on primary container
    secondary = Color(0xFF03DAC6), // Teal for secondary actions
    onSecondary = Color.White, // White text/icon on secondary
    secondaryContainer = Color(0xFFE0F7FA), // Light teal for containers
    onSecondaryContainer = Color.Black, // Black text on secondary container
    tertiary = Color(0xFF018786), // Darker teal for tertiary
    onTertiary = Color.White, // White text/icon on tertiary
    tertiaryContainer = Color(0xFFB2EBEA), // Light tertiary container
    onTertiaryContainer = Color.Black, // Black text on tertiary container
    background = Color.White, // White background for light theme
    onBackground = Color.Black, // Black text/icon on background
    surface = Color(0xFFF5F5F5), // Slightly off-white for surfaces
    onSurface = Color.Black, // Black text/icon on surface
    surfaceVariant = Color(0xFFE0E0E0), // Light gray for surface variant
    onSurfaceVariant = Color.Black, // Black text/icon on surface variant
    error = Color(0xFFB00020), // Red for errors
    onError = Color.White, // White text/icon on error
    errorContainer = Color(0xFFFDE7E9), // Light red for error containers
    onErrorContainer = Color.Black, // Black text on error container
    outline = Color(0xFF757575), // Gray for outlines
    outlineVariant = Color(0xFFB0B0B0), // Lighter gray for outline variant
    scrim = Color(0x80000000), // Semi-transparent black for scrims
    inverseSurface = Color(0xFF303030), // Dark gray for inverse surface
    inverseOnSurface = Color.White, // White text/icon on inverse surface
    inversePrimary = Color(0xFFD1C4E9) // Light purple for inverse primary
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD1C4E9), // Light purple for primary actions
    onPrimary = Color.Black, // Black text/icon on primary
    primaryContainer = Color(0xFF4A00B0), // Dark purple for containers
    onPrimaryContainer = Color.White, // White text on primary container
    secondary = Color(0xFF80CBC4), // Light teal for secondary actions
    onSecondary = Color.Black, // Black text/icon on secondary
    secondaryContainer = Color(0xFF00695C), // Dark teal for containers
    onSecondaryContainer = Color.White, // White text on secondary container
    tertiary = Color(0xFF4BAFAD), // Lighter teal for tertiary
    onTertiary = Color.Black, // Black text/icon on tertiary
    tertiaryContainer = Color(0xFF006362), // Darker teal for tertiary container
    onTertiaryContainer = Color.White, // White text on tertiary container
    background = Color(0xFF121212), // Dark background for dark theme
    onBackground = Color.White, // White text/icon on background
    surface = Color(0xFF1E1E1E), // Dark gray for surfaces
    onSurface = Color.White, // White text/icon on surface
    surfaceVariant = Color(0xFF424242), // Lighter dark gray for surface variant
    onSurfaceVariant = Color.White, // White text/icon on surface variant
    error = Color(0xFFCF6679), // Light red for errors
    onError = Color.Black, // Black text/icon on error
    errorContainer = Color(0xFF8B0000), // Dark red for error containers
    onErrorContainer = Color.White, // White text on error container
    outline = Color(0xFF8D8D8D), // Light gray for outlines
    outlineVariant = Color(0xFF616161), // Darker gray for outline variant
    scrim = Color(0x80000000), // Semi-transparent black for scrims
    inverseSurface = Color(0xFFE0E0E0), // Light gray for inverse surface
    inverseOnSurface = Color.Black, // Black text/icon on inverse surface
    inversePrimary = Color(0xFF6200EE) // Purple for inverse primary
)