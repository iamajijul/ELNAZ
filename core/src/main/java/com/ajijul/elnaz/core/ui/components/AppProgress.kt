package com.ajijul.elnaz.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSize
import com.ajijul.elnaz.core.utils.AppDimens.appProgressStroke

@Composable
fun AppProgress(
    modifier: Modifier = Modifier,
    size: Dp = appProgressSize
) {
    CircularProgressIndicator(
        strokeWidth = appProgressStroke,
        modifier = modifier.size(size)
    )
}

@Preview(showBackground = true)
@Composable
fun AppProgressPreview() {
    AppProgress()
}