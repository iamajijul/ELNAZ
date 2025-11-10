package com.ajijul.elnaz.domain.usecases.product

import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.product.ProductRepository

class AddItemUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(product: ProductModel) {
        repository.addProduct(product)
    }
}