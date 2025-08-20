package com.ajijul.elnaz.domain.repository

import com.ajijul.elnaz.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<ProductModel>>
    fun getInventoryStats(): Flow<Pair<Int, Double>>
    suspend fun addProduct(product: ProductModel)
    suspend fun getProductByBarcode(barcode: String): ProductModel?

}