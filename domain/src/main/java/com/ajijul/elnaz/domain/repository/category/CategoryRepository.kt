package com.ajijul.elnaz.domain.repository.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategorySeoModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.CategoryWithProductsModel
import com.ajijul.elnaz.domain.model.CategoryWithSEOModel
import com.ajijul.elnaz.domain.model.CategoryWithWarehouseModel
import com.ajijul.elnaz.domain.model.WarehouseWithCategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAllCategories(): Flow<List<CategoryModel>>
    fun getAllSubCategories(parentId: Long): Flow<List<CategoryModel>>
    suspend fun getCategoryById(id: Long): CategoryModel?
    suspend fun insertCategory(category: CategoryModel): Long
    suspend fun updateCategory(category: CategoryModel)
    suspend fun deleteCategory(categoryModel: CategoryModel)
    fun getCategoryWithProducts(categoryId: Long): Flow<CategoryWithProductsModel?>
    fun getCategoryWithWarehouses(categoryId: Long): Flow<CategoryWithWarehouseModel?>
    fun getCategoryWithDiscounts(categoryId: Long): Flow<CategoryWithDiscountsModel?>
    suspend fun getCategoryWithSEO(categoryId: Long): CategoryWithSEOModel?

    suspend fun linkCategoryToProduct(categoryId: Long, productId: Long)
    suspend fun unlinkCategoryFromProducts(categoryId: Long, productId: Long)
    suspend fun clearProductsForCategory(categoryId: Long)

    suspend fun linkCategoryToWarehouse(categoryId: Long, warehouseId: Long)
    suspend fun unlinkCategoryFromWarehouse(categoryId: Long, warehouseId: Long)
    suspend fun clearWarehousesForCategory(categoryId: Long)

    suspend fun linkCategoryToDiscount(categoryId: Long, discountId: Long)
    suspend fun unlinkCategoryFromDiscount(categoryId: Long, discountId: Long)
    suspend fun clearDiscountsForCategory(categoryId: Long)

    suspend fun insertOrUpdateSEO(seo: CategorySeoModel): Long
    suspend fun deleteSEO(categoryId: Long)
}
