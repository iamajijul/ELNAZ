package com.ajijul.elnaz.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppIconButton(
    onClick: () -> Unit,
    iconImage: ImageVector,
    imageDescription: String? = null
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = iconImage, contentDescription = imageDescription)
    }
}