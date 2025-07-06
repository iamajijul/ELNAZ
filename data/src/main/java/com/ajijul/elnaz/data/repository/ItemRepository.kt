package com.ajijul.elnaz.data.repository

import com.ajijul.elnaz.domain.model.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val dataFlowManager: DataFlowManager
) {
    fun getAllItems(): Flow<List<Item>> = dataFlowManager.itemsFlow

    suspend fun addItem(item: Item) {
        val entity = item.toEntity()
        itemDao.insertItem(entity)
        dataFlowManager.syncData()
    }

    suspend fun getItemByBarcode(barcode: String): Item? {
        return itemDao.getItemByBarcode(barcode)?.toDomain()
    }

    fun getInventoryStats(): Flow<Pair<Int, Double>> = dataFlowManager.inventoryStatsFlow

    fun getLowStockItems(threshold: Int = 0): Flow<List<Item>> {
        return itemDao.getLowStockItems(threshold).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    fun getOldStockItems(timestamp: Long): Flow<List<Item>> {
        return itemDao.getOldStockItems(timestamp).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    private fun ItemEntity.toDomain() = Item(
        id = id,
        name = name,
        barcode = barcode,
        quantity = quantity,
        price = price,
        lastUpdated = lastUpdated,
        createdAt = createdAt,
        location = location
    )
    private fun Item.toEntity() = ItemEntity(
        id = id,
        name = name,
        barcode = barcode,
        quantity = quantity,
        price = price,
        lastUpdated = lastUpdated,
        createdAt = createdAt,
        location = location
    )
}