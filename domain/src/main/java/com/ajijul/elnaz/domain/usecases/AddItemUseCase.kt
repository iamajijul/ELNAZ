package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.domain.model.Item
import com.ajijul.elnaz.domain.repository.ItemRepository

class AddItemUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(item: Item) {
        repository.addItem(item)
    }
}