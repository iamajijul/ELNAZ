package com.ajijul.elnaz.inventory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ajijul.elnaz.core.ui.components.ItemCard

@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel = hiltViewModel(),
    onNavigateToAddItem: () -> Unit,
    onNavigateToBarcodeScanner: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total Items: ${state.itemCount}")
            Text("Total Value: $${state.totalValue}")
        }
/*        if (state.lowStockProducts.isNotEmpty()) {
            Text(
                text = "⚠️ Out of Stock Items (${state.lowStockProducts.size})",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
            state.lowStockProducts.forEach { item ->
                ItemCard(item)
            }
        }
        if (state.oldStockProducts.isNotEmpty()) {
            Text(
                text = "⚠️ Stock Older Than 1 Year (${state.oldStockProducts.size})",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
            state.oldStockProducts.forEach { item ->
                ItemCard(item)
            }
        }*/
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onNavigateToAddItem,
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Add Item")
            }
            Button(
                onClick = onNavigateToBarcodeScanner,
                modifier = Modifier.weight(1f)
            ) {
                Text("Scan Barcode")
            }
        }

        LazyColumn {
            items(state.products) { item ->
                ItemCard(item)
            }
        }
    }
}