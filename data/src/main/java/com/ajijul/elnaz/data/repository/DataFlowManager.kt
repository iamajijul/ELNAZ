package com.ajijul.elnaz.data.repository

import com.ajijul.elnaz.data.local.dao.ProductDao
import com.ajijul.elnaz.data.local.entity.Product
import com.ajijul.elnaz.data.mapper.toDomain
import com.ajijul.elnaz.data.network.FirebaseFirestoreDataSource
import com.ajijul.elnaz.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataFlowManager @Inject constructor(
    private val productDao: ProductDao,
    private val firebaseFirestoreDataSource: FirebaseFirestoreDataSource
) {
    val itemsFlow: Flow<List<ProductModel>> = productDao.getAllProducts().map { entities ->
        entities.map { it.toDomain() }
    }

//    val inventoryStatsFlow: Flow<Pair<Int, Double>> = combine(
//        productDao.getItemCount(),
//        productDao.getTotalInventoryValue()
//    ) { count, totalValue ->
//        Pair(count, totalValue ?: 0.0)
//    }

    suspend fun syncData() {
        try {
            val localItems = productDao.getAllProducts().map { it }.first()
            val remoteItems = firebaseFirestoreDataSource.getItems()
            val mergedItems = mergeItems(localItems, remoteItems)
            productDao.insertAllProducts(mergedItems)
            firebaseFirestoreDataSource.syncItems(mergedItems)
        } catch (e: Exception) {
            // Offline: preserve local data
        }
    }

    private fun mergeItems(local: List<Product>, remote: List<Product>): List<Product> {
        val remoteMap = remote.associateBy { it.barcode }
        return local.map { localItem ->
            remoteMap[localItem.barcode]?.let { remoteItem ->
                if (remoteItem.lastUpdated > localItem.lastUpdated) remoteItem else localItem
            } ?: localItem
        }.union(remote.filter { it.barcode !in local.map { l -> l.barcode } }).toList()
    }
}