package com.ajijul.elnaz.data.repository

import com.ajijul.elnaz.data.local.dao.ItemDao
import com.ajijul.elnaz.data.local.entity.ItemEntity
import com.ajijul.elnaz.data.remote.FirebaseDataSource
import com.ajijul.elnaz.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataFlowManager @Inject constructor(
    private val itemDao: ItemDao,
    private val firebaseDataSource: FirebaseDataSource
) {
    val itemsFlow: Flow<List<Item>> = itemDao.getAllItems().map { entities ->
        entities.map { it.toDomain() }
    }

    val inventoryStatsFlow: Flow<Pair<Int, Double>> = combine(
        itemDao.getItemCount(),
        itemDao.getTotalInventoryValue()
    ) { count, totalValue ->
        Pair(count, totalValue ?: 0.0)
    }

    suspend fun syncData() {
        try {
            val localItems = itemDao.getAllItems().map { it }.first()
            val remoteItems = firebaseDataSource.getItems()
            val mergedItems = mergeItems(localItems, remoteItems)
            itemDao.insertItems(mergedItems)
            firebaseDataSource.syncItems(mergedItems)
        } catch (e: Exception) {
            // Offline: preserve local data
        }
    }

    private fun mergeItems(local: List<ItemEntity>, remote: List<ItemEntity>): List<ItemEntity> {
        val remoteMap = remote.associateBy { it.barcode }
        return local.map { localItem ->
            remoteMap[localItem.barcode]?.let { remoteItem ->
                if (remoteItem.lastUpdated > localItem.lastUpdated) remoteItem else localItem
            } ?: localItem
        }.union(remote.filter { it.barcode !in local.map { l -> l.barcode } }).toList()
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
}