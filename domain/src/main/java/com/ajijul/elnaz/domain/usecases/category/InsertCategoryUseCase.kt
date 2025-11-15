package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class InsertCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryModel: CategoryModel): Long =
        categoryRepository.insertCategory(categoryModel)

}