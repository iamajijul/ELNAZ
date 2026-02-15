package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class DeleteCategorySEOUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(id: String): Resource<Int> =
        categoryRepository.deleteSEO(categoryId = id)

}