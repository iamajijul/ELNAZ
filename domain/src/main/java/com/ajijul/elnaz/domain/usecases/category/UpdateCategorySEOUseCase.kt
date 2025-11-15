package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategorySeoModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class UpdateCategorySEOUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(seoModel: CategorySeoModel): Unit =
        categoryRepository.insertOrUpdateSEO(seoModel)

}