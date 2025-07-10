package com.ajijul.elnaz.domain.repository

import com.ajijul.elnaz.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getAllItems(): Flow<List<Item>>
    fun getInventoryStats(): Flow<Pair<Int, Double>>
    fun getLowStockItems(threshold: Int = 0) : Flow<List<Item>>
    fun getOldStockItems(timestamp: Long): Flow<List<Item>>
    suspend fun addItem(item: Item)
    suspend fun getItemByBarcode(barcode: String): Item?

}