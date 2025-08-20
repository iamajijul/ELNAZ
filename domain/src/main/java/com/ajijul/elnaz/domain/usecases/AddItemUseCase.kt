package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.ProductRepository

class AddItemUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(product: ProductModel) {
        repository.addProduct(product)
    }
}