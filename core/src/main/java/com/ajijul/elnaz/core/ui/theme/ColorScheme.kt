package com.ajijul.elnaz.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.ajijul.elnaz.resources.R
import androidx.compose.material3.ColorScheme

@Composable
fun lightColorSchemeFromXml(): ColorScheme {
    return lightColorScheme(
        primary = colorResource(id = R.color.color_primary),
        onPrimary = colorResource(R.color.color_on_primary),
        primaryContainer = colorResource(R.color.color_primary_container),
        onPrimaryContainer = colorResource(R.color.color_on_primary_container),
        secondary = colorResource(R.color.color_secondary),
        onSecondary = colorResource(R.color.color_on_secondary),
        secondaryContainer = colorResource(R.color.color_secondary_container),
        onSecondaryContainer = colorResource(R.color.color_on_secondary_container),
        tertiary = colorResource(R.color.color_tertiary),
        onTertiary = colorResource(R.color.color_on_tertiary),
        tertiaryContainer = colorResource(R.color.color_tertiary_container),
        onTertiaryContainer = colorResource(R.color.color_on_tertiary_container),
        background = colorResource(R.color.color_background),
        onBackground = colorResource(R.color.color_on_background),
        surface = colorResource(R.color.color_surface),
        onSurface = colorResource(R.color.color_on_surface),
        surfaceVariant = colorResource(R.color.color_surface_variant),
        onSurfaceVariant = colorResource(R.color.color_on_surface_variant),
        error = colorResource(R.color.color_error),
        onError = colorResource(R.color.color_on_error),
        errorContainer = colorResource(R.color.color_error_container),
        onErrorContainer = colorResource(R.color.color_on_error_container),
        outline = colorResource(R.color.color_outline),
        outlineVariant = colorResource(R.color.color_outline_variant),
        scrim = colorResource(R.color.color_scrim),
        inverseSurface = colorResource(R.color.color_inverse_surface),
        inverseOnSurface = colorResource(R.color.color_inverse_on_surface),
        inversePrimary = colorResource(R.color.color_inverse_primary)
    )
}

@Composable
fun darkColorSchemeFromXml(): ColorScheme {
    return darkColorScheme(
        primary = colorResource(R.color.color_primary),
        onPrimary = colorResource(R.color.color_on_primary),
        primaryContainer = colorResource(R.color.color_primary_container),
        onPrimaryContainer = colorResource(R.color.color_on_primary_container),
        secondary = colorResource(R.color.color_secondary),
        onSecondary = colorResource(R.color.color_on_secondary),
        secondaryContainer = colorResource(R.color.color_secondary_container),
        onSecondaryContainer = colorResource(R.color.color_on_secondary_container),
        tertiary = colorResource(R.color.color_tertiary),
        onTertiary = colorResource(R.color.color_on_tertiary),
        tertiaryContainer = colorResource(R.color.color_tertiary_container),
        onTertiaryContainer = colorResource(R.color.color_on_tertiary_container),
        background = colorResource(R.color.color_background),
        onBackground = colorResource(R.color.color_on_background),
        surface = colorResource(R.color.color_surface),
        onSurface = colorResource(R.color.color_on_surface),
        surfaceVariant = colorResource(R.color.color_surface_variant),
        onSurfaceVariant = colorResource(R.color.color_on_surface_variant),
        error = colorResource(R.color.color_error),
        onError = colorResource(R.color.color_on_error),
        errorContainer = colorResource(R.color.color_error_container),
        onErrorContainer = colorResource(R.color.color_on_error_container),
        outline = colorResource(R.color.color_outline),
        outlineVariant = colorResource(R.color.color_outline_variant),
        scrim = colorResource(R.color.color_scrim),
        inverseSurface = colorResource(R.color.color_inverse_surface),
        inverseOnSurface = colorResource(R.color.color_inverse_on_surface),
        inversePrimary = colorResource(R.color.color_inverse_primary)
    )
}


