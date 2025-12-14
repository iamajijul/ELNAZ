package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategorySeoModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository

class UpdateCategorySEOUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(seoModel: CategorySeoModel): Long =
        categoryRepository.insertOrUpdateSEO(seoModel)

}