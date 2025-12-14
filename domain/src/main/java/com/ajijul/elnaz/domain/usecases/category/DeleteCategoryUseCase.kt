package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.repository.category.CategoryRepository

class DeleteCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(id: Long): Unit =
        categoryRepository.deleteCategoryById(categoryId = id)

}