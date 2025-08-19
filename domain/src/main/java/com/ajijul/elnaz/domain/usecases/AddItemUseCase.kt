package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.ItemRepository

class AddItemUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(product: ProductModel) {
        repository.addItem(product)
    }
}