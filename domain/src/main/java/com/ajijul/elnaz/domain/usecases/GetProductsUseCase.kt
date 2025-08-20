package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase  constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<ProductModel>> {
        return repository.getAllProducts()
    }
}