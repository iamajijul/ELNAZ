package com.ajijul.elnaz.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ajijul.elnaz.core.utils.AppDimens

@Composable
fun PrimaryFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = ButtonDefaults.shape,
        colors = colors,
        elevation = elevation,
        border = null,
        contentPadding = contentPadding,
        interactionSource = null,
        content = content
    )
}

@Composable
fun PrimaryOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    stroke: BorderStroke? = BorderStroke(AppDimens.appStroke, MaterialTheme.colorScheme.primary),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = ButtonDefaults.outlinedShape,
        colors = colors,
        elevation = elevation,
        border = stroke,
        contentPadding = contentPadding,
        interactionSource = null,
        content = content
    )
}

@Composable
fun PrimaryTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = ButtonDefaults.textShape,
        colors = colors,
        elevation = elevation,
        border = null,
        contentPadding = contentPadding,
        interactionSource = null,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun ShowPrimaryFilledButton() {
    PrimaryFilledButton(onClick = {}) {
        Text("Click Me")
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowPrimaryOutlineButton() {
    PrimaryOutlineButton(onClick = {}) {
        Text("Click Me")
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowPrimaryTextButton() {
    PrimaryTextButton(onClick = {}) {
        Text("Click Me")
    }
}