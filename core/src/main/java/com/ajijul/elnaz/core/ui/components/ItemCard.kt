package com.ajijul.elnaz.core.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajijul.elnaz.domain.model.ProductModel

@Composable
fun ItemCard(product: ProductModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Barcode: ${product.barcode}")
            Text(text = "Buy Price: $${product.buyPrice}")
            Text(text = "Sale Price: $${product.salePrice}")
        }
    }
}