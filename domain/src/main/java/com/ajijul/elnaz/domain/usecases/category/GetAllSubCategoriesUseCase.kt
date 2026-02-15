package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllSubCategoriesUseCase(val categoryRepository: CategoryRepository) {

    operator fun invoke(parentCategoryId: String): Flow<Resource<List<CategoryModel>>> =
        categoryRepository.getAllSubCategories(parentCategoryId)

}