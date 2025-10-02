package com.ajijul.elnaz.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSize
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSmallSize
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSmallStroke
import com.ajijul.elnaz.core.utils.AppDimens.appProgressStroke

@Composable
fun AppProgress(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.circularColor,
    size: Dp = appProgressSize,
    strokeWidth: Dp = appProgressStroke
) {
    CircularProgressIndicator(
        strokeWidth = strokeWidth,
        color = color,
        modifier = modifier.size(size)
    )
}

@Preview(showBackground = true)
@Composable
fun AppProgressPreview() {
    AppProgress()
}