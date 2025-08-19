package com.ajijul.elnaz.domain.repository

import com.ajijul.elnaz.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getAllItems(): Flow<List<ProductModel>>
    fun getInventoryStats(): Flow<Pair<Int, Double>>
    fun getLowStockItems(threshold: Int = 0) : Flow<List<ProductModel>>
    fun getOldStockItems(timestamp: Long): Flow<List<ProductModel>>
    suspend fun addItem(product: ProductModel)
    suspend fun getItemByBarcode(barcode: String): ProductModel?

}