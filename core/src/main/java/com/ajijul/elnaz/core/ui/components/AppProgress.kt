package com.ajijul.elnaz.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSize
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSmallSize
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSmallStroke
import com.ajijul.elnaz.core.utils.AppDimens.appProgressStroke
import com.ajijul.elnaz.core.utils.AppDimens.roundedCornerSize
import com.ajijul.elnaz.resources.R

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

@Composable
fun AppProgressOnScreen(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.circularColor,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.color_low_opacity_background)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(roundedCornerSize))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            AppProgress(
                modifier = modifier,
                color = color,
                size = appProgressSmallSize,
                strokeWidth = appProgressSmallStroke
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppProgressPreview() {
    AppProgress()
}

@Preview(showBackground = true)
@Composable
fun AppProgressOnScreenPreview() {
    AppProgressOnScreen()
}