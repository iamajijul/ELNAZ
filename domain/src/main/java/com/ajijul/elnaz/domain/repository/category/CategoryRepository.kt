package com.ajijul.elnaz.domain.repository.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategorySeoModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.CategoryWithProductsModel
import com.ajijul.elnaz.domain.model.CategoryWithSEOModel
import com.ajijul.elnaz.domain.model.CategoryWithWarehouseModel
import com.ajijul.elnaz.domain.model.WarehouseWithCategoryModel
import com.ajijul.elnaz.domain.model.enums.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAllCategories(): Flow<Resource<List<CategoryModel>>>
    fun getAllSubCategories(parentId: String): Flow<Resource<List<CategoryModel>>>
    suspend fun getCategoryById(id: String): Flow<Resource<CategoryModel?>>
    suspend fun insertCategory(category: CategoryModel): Resource<Long>
    suspend fun updateCategory(category: CategoryModel): Resource<Int>
    suspend fun deleteCategory(categoryModel: CategoryModel): Resource<Int>
    suspend fun deleteCategoryById(categoryId: String): Resource<Int>
    fun getCategoryWithProducts(categoryId: String): Flow<Resource<CategoryWithProductsModel?>>
    fun getCategoryWithWarehouses(categoryId: String): Flow<Resource<CategoryWithWarehouseModel?>>
    fun getCategoryWithDiscounts(categoryId: String): Flow<Resource<CategoryWithDiscountsModel?>>
    suspend fun getCategoryWithSEO(categoryId: String): Flow<Resource<CategoryWithSEOModel?>>

    suspend fun linkCategoryToProduct(
        shopId: String,
        categoryId: String,
        productId: String
    ): Resource<Long>

    suspend fun unlinkCategoryFromProducts(categoryId: String, productId: String): Resource<Int>
    suspend fun clearProductsForCategory(categoryId: String): Resource<Int>

    suspend fun linkCategoryToWarehouse(
        shopId: String,
        categoryId: String,
        warehouseId: String
    ): Resource<Long>

    suspend fun unlinkCategoryFromWarehouse(categoryId: String, warehouseId: String): Resource<Int>
    suspend fun clearWarehousesForCategory(categoryId: String): Resource<Int>

    suspend fun linkCategoryToDiscount(
        shopId: String,
        categoryId: String,
        discountId: String
    ): Resource<Long>

    suspend fun unlinkCategoryFromDiscount(categoryId: String, discountId: String): Resource<Int>
    suspend fun clearDiscountsForCategory(categoryId: String): Resource<Int>

    suspend fun insertOrUpdateSEO(seo: CategorySeoModel): Resource<Long>
    suspend fun deleteSEO(categoryId: String): Resource<Int>

    suspend fun syncCategories() // Pull from Firebase
}
