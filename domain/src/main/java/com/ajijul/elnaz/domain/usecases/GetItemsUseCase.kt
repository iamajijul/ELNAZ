package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.domain.model.Item
import com.ajijul.elnaz.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase  constructor(
    private val repository: ItemRepository
) {
    operator fun invoke(): Flow<List<Item>> {
        return repository.getAllItems()
    }
}