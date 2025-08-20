package com.ajijul.elnaz.data.repository

import com.ajijul.elnaz.data.local.dao.ProductDao
import com.ajijul.elnaz.data.mapper.toDomain
import com.ajijul.elnaz.data.mapper.toProductEntity
import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ProductRepositoryImplementation @Inject constructor(
    private val productDao: ProductDao,
    private val dataFlowManager: DataFlowManager
) : ProductRepository {

    override fun getAllProducts(): Flow<List<ProductModel>> = dataFlowManager.itemsFlow

    override suspend fun addProduct(product: ProductModel) {
        val entity = product.toProductEntity()
        productDao.insertProduct(entity)
        dataFlowManager.syncData()
    }

    override suspend fun getProductByBarcode(barcode: String): ProductModel? {
        return productDao.getProductByBarcode(barcode)?.toDomain()
    }

    override fun getInventoryStats(): Flow<Pair<Int, Double>> = flowOf(Pair(0, 0.0))

}