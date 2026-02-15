package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.enums.AppError
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import com.ajijul.elnaz.domain.utils.DomainConstants.MIN_CATEGORY_NAME_LENGTH

class UpdateOrInsertCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryModel: CategoryModel): Resource<Long> {

        if (categoryModel.name.isBlank())
            return Resource.Error(AppError.Validation.FieldEmpty)

        if (categoryModel.name.trim().length < MIN_CATEGORY_NAME_LENGTH)
            return Resource.Error(AppError.Validation.MinLength(MIN_CATEGORY_NAME_LENGTH))

        val result: Resource<Long> = (if (categoryModel.id.isEmpty()) {
            categoryRepository.insertCategory(categoryModel)
        } else {
            when (val updateResult = categoryRepository.updateCategory(categoryModel)) {
                is Resource.Success -> {
                    val rowsAffected = updateResult.data.toLong()
                    Resource.Success(rowsAffected)
                }
                is Resource.Error -> {
                    Resource.Error(updateResult.error)
                }
                is Resource.Loading -> {
                    Resource.Loading
                }
            }
        })

        return result
    }

}