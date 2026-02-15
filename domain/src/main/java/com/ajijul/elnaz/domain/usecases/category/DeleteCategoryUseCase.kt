package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository

class DeleteCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(id: String): Resource<Int> =
        categoryRepository.deleteCategoryById(categoryId = id)

}