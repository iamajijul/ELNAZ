package com.ajijul.elnaz.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ajijul.elnaz.core.ui.theme.AppDimens
import com.ajijul.elnaz.resources.R

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        style = style,
        color = color,
        textAlign = textAlign,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow,
        fontWeight = fontWeight
    )
}

@Composable
fun AppTextOnFilledButton(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    textAlign: TextAlign? = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        style = style,
        color = color,
        textAlign = textAlign,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Composable
fun AppTextEmptyScreen(
    text: String,
    modifier: Modifier = Modifier,
    icon : ImageVector? = null,
    iconModifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    fontWeight: FontWeight = FontWeight.Normal
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimens.verticalIconTextGap)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = iconModifier
                )
            }
            AppText(
                text = text,
                style = style,
                color = color,
                textAlign = textAlign,
                modifier = modifier,
                maxLines = maxLines,
                overflow = overflow,
                fontWeight = fontWeight
            )


        }
    }

}

@Preview(showBackground = true)
@Composable
fun AppTextPreview() {
    AppText(
        text = "Elnaz"
    )
}

@Preview(showBackground = true)
@Composable
fun AppTextPreviewOnFilledButton() {
    AppTextOnFilledButton(
        text = "Elnaz"
    )
}

@Preview(showBackground = true)
@Composable
fun AppTextPreviewOnEmptyScreen() {
    AppTextEmptyScreen(
        text = "Something went wrong",
        icon = Icons.Default.AddAlert,
    )
}
