package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class UnlinkDiscountFromCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryId: Long, discountId: Long): Unit =
        categoryRepository.unlinkCategoryFromDiscount(categoryId, discountId)
}