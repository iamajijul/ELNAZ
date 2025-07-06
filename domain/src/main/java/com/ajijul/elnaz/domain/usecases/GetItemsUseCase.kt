package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.data.repository.ItemRepository
import com.ajijul.elnaz.domain.model.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    operator fun invoke(): Flow<List<Item>> {
        return repository.getAllItems()
    }
}