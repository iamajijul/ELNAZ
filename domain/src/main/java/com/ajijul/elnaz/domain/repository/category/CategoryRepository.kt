package com.ajijul.elnaz.domain.repository.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.enums.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<CategoryModel>>
    suspend fun getCategoryById(id: Long): CategoryModel?
    suspend fun addCategory(category: CategoryModel): Resource<Long>
    suspend fun updateCategory(category: CategoryModel): Resource<Unit>
    suspend fun deleteCategory(id: Long): Resource<Unit>
    suspend fun syncWithFirestore(): Resource<Unit>
}