package com.ajijul.elnaz.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ajijul.elnaz.core.utils.AppDimens.formGap
import com.ajijul.elnaz.core.utils.AppDimens.screenPadding

@Composable
fun ItemOnCenteredColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(formGap, Alignment.CenterVertically),
        content = content
    )
}

@Composable
fun ItemOnFormColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(formGap),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewItemsOnCenteredColumn() {
    ItemOnCenteredColumn {
        AppText(text = "Hello", style = MaterialTheme.typography.titleLarge)
    }
}