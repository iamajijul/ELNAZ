package com.ajijul.elnaz.data.repository.category

import com.ajijul.elnaz.data.local.dao.CategoryDao
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRef
import com.ajijul.elnaz.data.local.entity.category.CategoryProductCrossRef
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRef
import com.ajijul.elnaz.data.mapper.toDomain
import com.ajijul.elnaz.data.mapper.toEntity
import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategorySeoModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.CategoryWithProductsModel
import com.ajijul.elnaz.domain.model.CategoryWithSEOModel
import com.ajijul.elnaz.domain.model.CategoryWithWarehouseModel
import com.ajijul.elnaz.domain.model.WarehouseWithCategoryModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<CategoryModel>> =
        categoryDao.getAllCategoriesFlow().map { categoryList ->
            categoryList.map { it.toDomain() }
        }

    override fun getAllSubCategories(parentId: Long): Flow<List<CategoryModel>> =
        categoryDao.getAllSubCategories(parentId).map { categoryList ->
            categoryList.map { it.toDomain() }
        }

    override suspend fun getCategoryById(id: Long): CategoryModel? =
        categoryDao.getCategoryById(id)?.toDomain()

    override suspend fun insertCategory(category: CategoryModel): Long =
        categoryDao.insertCategory(category.toEntity())


    override suspend fun updateCategory(category: CategoryModel) =
        categoryDao.updateCategory(category.toEntity())

    override suspend fun deleteCategory(categoryModel: CategoryModel) =
        categoryDao.deleteCategory(categoryModel.toEntity())

    override suspend fun deleteCategoryById(categoryId: Long) {
        categoryDao.deleteCategoryById(categoryId)
    }

    override fun getCategoryWithProducts(categoryId: Long): Flow<CategoryWithProductsModel?> =
        categoryDao.getCategoryWithProducts(categoryId).map { categoryWithProducts ->
            categoryWithProducts?.toDomain()
        }

    override fun getCategoryWithWarehouses(categoryId: Long): Flow<CategoryWithWarehouseModel?> =
        categoryDao.getCategoryWithWarehouses(categoryId).map { categoryWithWarehouses ->
            categoryWithWarehouses?.toDomain()
        }

    override fun getCategoryWithDiscounts(categoryId: Long): Flow<CategoryWithDiscountsModel?> =
        categoryDao.getCategoryWithDiscounts(categoryId).map { categoryWithDiscounts ->
            categoryWithDiscounts?.toDomain()
        }

    override suspend fun getCategoryWithSEO(categoryId: Long): CategoryWithSEOModel? =
        categoryDao.getCategoryWithSEO(categoryId)?.toDomain()

    override suspend fun linkCategoryToProduct(categoryId: Long, productId: Long) =
        categoryDao.insertCategoryProductCrossRef(CategoryProductCrossRef(categoryId, productId))

    override suspend fun unlinkCategoryFromProducts(
        categoryId: Long,
        productId: Long
    ) = categoryDao.deleteCategoryProductCrossRef(categoryId, productId)


    override suspend fun clearProductsForCategory(categoryId: Long) =
        categoryDao.clearProductForCategory(categoryId)

    override suspend fun linkCategoryToWarehouse(
        categoryId: Long,
        warehouseId: Long
    ) = categoryDao.insertCategoryWarehouseCrossRef(
        CategoryWarehouseCrossRef(categoryId, warehouseId)
    )

    override suspend fun unlinkCategoryFromWarehouse(
        categoryId: Long,
        warehouseId: Long
    ) = categoryDao.deleteCategoryWarehouseCrossRef(categoryId, warehouseId)

    override suspend fun clearWarehousesForCategory(categoryId: Long) =
        categoryDao.clearWarehousesForCategory(categoryId)

    override suspend fun linkCategoryToDiscount(categoryId: Long, discountId: Long) =
        categoryDao.insertCategoryDiscountCrossRef(
            CategoryDiscountCrossRef(categoryId, discountId)
        )

    override suspend fun unlinkCategoryFromDiscount(
        categoryId: Long,
        discountId: Long
    ) = categoryDao.deleteCategoryDiscountCrossRef(categoryId, discountId)

    override suspend fun clearDiscountsForCategory(categoryId: Long) =
        categoryDao.clearDiscountsForCategory(categoryId)

    override suspend fun insertOrUpdateSEO(seo: CategorySeoModel): Long =
        categoryDao.insertOrUpdateCategorySEO(seo.toEntity())

    override suspend fun deleteSEO(categoryId: Long) = categoryDao.deleteSEOForCategory(categoryId)

}