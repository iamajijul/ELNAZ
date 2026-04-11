package com.ajijul.elnaz.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable


@Composable
fun navBarItemColorCombination() : NavigationBarItemColors = NavigationBarItemDefaults.colors(
    // SELECTED STATE
    indicatorColor = MaterialTheme.colorScheme.primary,
    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
    selectedTextColor = MaterialTheme.colorScheme.primary,

    // UNSELECTED STATE
    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
)