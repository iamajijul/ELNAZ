package com.ajijul.elnaz.data.repository

import com.ajijul.elnaz.data.local.dao.ProductDao
import com.ajijul.elnaz.data.mapper.toDomain
import com.ajijul.elnaz.data.mapper.toProductEntity
import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ItemRepositoryImplementation @Inject constructor(
    private val productDao: ProductDao,
    private val dataFlowManager: DataFlowManager
) : ItemRepository {
    override fun getAllItems(): Flow<List<ProductModel>> = dataFlowManager.itemsFlow

    override suspend fun addItem(product: ProductModel) {
        val entity = product.toProductEntity()
        productDao.insertItem(entity)
        dataFlowManager.syncData()
    }

    override suspend fun getItemByBarcode(barcode: String): ProductModel? {
        return productDao.getItemByBarcode(barcode)?.toDomain()
    }

    override fun getInventoryStats(): Flow<Pair<Int, Double>> = flowOf(Pair(0, 0.0))

    override fun getLowStockItems(threshold: Int): Flow<List<ProductModel>> {
        return flowOf(emptyList<ProductModel>())
    }

    override fun getOldStockItems(timestamp: Long): Flow<List<ProductModel>> {
        return flowOf(emptyList<ProductModel>())
    }
}