package com.ajijul.elnaz.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ajijul.elnaz.core.utils.AppDimens.formGap

@Composable
fun AppRows(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = formGap, Alignment.CenterHorizontally),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemsOnCenteredColumn() {
    AppRows {
        AppText(text = "Hello", style = MaterialTheme.typography.titleLarge)
        AppText(text = "Hello", style = MaterialTheme.typography.titleLarge)
    }
}