package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class LinkCategoryToDiscountUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(
        shopId: String,
        categoryId: String,
        discountId: String
    ): Resource<Long> =
        categoryRepository.linkCategoryToDiscount(shopId, categoryId, discountId)

}