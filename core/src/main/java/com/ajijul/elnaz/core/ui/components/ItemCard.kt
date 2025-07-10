package com.ajijul.elnaz.core.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajijul.elnaz.core.model.Item

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Barcode: ${item.barcode}")
            Text(text = "Quantity: ${item.quantity}")
            Text(text = "Price: $${item.price}")
            Text(text = "Location: ${item.location}")
            Text(text = "Created: ${item.createdAt}") // Optional: Display creation date
        }
    }
}