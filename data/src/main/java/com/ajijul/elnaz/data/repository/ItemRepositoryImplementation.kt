package com.ajijul.elnaz.data.repository

import com.ajijul.elnaz.domain.model.Item
import com.ajijul.elnaz.data.local.dao.ItemDao
import com.ajijul.elnaz.data.mapper.toDataItemEntity
import com.ajijul.elnaz.data.mapper.toDomainItem
import com.ajijul.elnaz.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImplementation @Inject constructor(
    private val itemDao: ItemDao,
    private val dataFlowManager: DataFlowManager
) : ItemRepository {
    override fun getAllItems(): Flow<List<Item>> = dataFlowManager.itemsFlow

    override suspend fun addItem(item: Item) {
        val entity = item.toDataItemEntity()
        itemDao.insertItem(entity)
        dataFlowManager.syncData()
    }

    override suspend fun getItemByBarcode(barcode: String): Item? {
        return itemDao.getItemByBarcode(barcode)?.toDomainItem()
    }

    override fun getInventoryStats(): Flow<Pair<Int, Double>> = dataFlowManager.inventoryStatsFlow

    override fun getLowStockItems(threshold: Int): Flow<List<Item>> {
        return itemDao.getLowStockItems(threshold).map { entities ->
            entities.map { it.toDomainItem() }
        }
    }

    override fun getOldStockItems(timestamp: Long): Flow<List<Item>> {
        return itemDao.getOldStockItems(timestamp).map { entities ->
            entities.map { it.toDomainItem() }
        }
    }
}