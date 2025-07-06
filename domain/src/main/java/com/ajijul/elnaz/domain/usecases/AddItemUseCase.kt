package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.data.repository.ItemRepository
import com.ajijul.elnaz.domain.model.Item
import javax.inject.Inject

class AddItemUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(item: Item) {
        repository.addItem(item)
    }
}