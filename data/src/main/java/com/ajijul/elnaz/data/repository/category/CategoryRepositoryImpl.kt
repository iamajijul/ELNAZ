package com.ajijul.elnaz.data.repository.category

import com.ajijul.elnaz.data.local.dao.CategoryDao
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRefEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryProductCrossRefEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRefEntity
import com.ajijul.elnaz.data.mapToDomainResource
import com.ajijul.elnaz.data.mapper.toDomain
import com.ajijul.elnaz.data.mapper.toEntity
import com.ajijul.elnaz.data.network.FirebaseFirestoreDataSource
import com.ajijul.elnaz.data.network.firebase.FirestoreCollections
import com.ajijul.elnaz.data.safeCall
import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategorySeoModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.CategoryWithProductsModel
import com.ajijul.elnaz.domain.model.CategoryWithSEOModel
import com.ajijul.elnaz.domain.model.CategoryWithWarehouseModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val firebaseFirestoreDataSource: FirebaseFirestoreDataSource
) : CategoryRepository {

    override fun getAllCategories(): Flow<Resource<List<CategoryModel>>> =
        categoryDao.getAllCategoriesFlow()
            .mapToDomainResource { categoryList ->
                categoryList.map { it.toDomain() }
            }


    override fun getAllSubCategories(parentId: String): Flow<Resource<List<CategoryModel>>> =
        categoryDao.getAllSubCategories(parentId).mapToDomainResource { categoryList ->
            categoryList.map { it.toDomain() }
        }

    override suspend fun getCategoryById(id: String): Flow<Resource<CategoryModel?>> =
        categoryDao.getCategoryById(id).mapToDomainResource { category ->
            category?.toDomain()
        }

    override suspend fun insertCategory(category: CategoryModel): Resource<Long> =
        safeCall {
            categoryDao.insertCategory(category.toEntity())
        }

    override suspend fun updateCategory(category: CategoryModel): Resource<Int> =
        safeCall {
            categoryDao.updateCategory(category.toEntity())
        }

    override suspend fun deleteCategory(categoryModel: CategoryModel): Resource<Int> =
        safeCall {
            categoryDao.deleteCategory(categoryModel.toEntity())
        }

    override suspend fun deleteCategoryById(categoryId: String): Resource<Int> = safeCall {
        categoryDao.deleteCategoryById(categoryId)
    }

    override fun getCategoryWithProducts(categoryId: String): Flow<Resource<CategoryWithProductsModel?>> =
        categoryDao.getCategoryWithProducts(categoryId)
            .mapToDomainResource { categoryWithProducts ->
                categoryWithProducts?.toDomain()
            }

    override fun getCategoryWithWarehouses(categoryId: String): Flow<Resource<CategoryWithWarehouseModel?>> =
        categoryDao.getCategoryWithWarehouses(categoryId)
            .mapToDomainResource { categoryWithWarehouses ->
                categoryWithWarehouses?.toDomain()
            }

    override fun getCategoryWithDiscounts(categoryId: String): Flow<Resource<CategoryWithDiscountsModel?>> =
        categoryDao.getCategoryWithDiscounts(categoryId)
            .mapToDomainResource { categoryWithDiscounts ->
                categoryWithDiscounts?.toDomain()
            }

    override suspend fun getCategoryWithSEO(categoryId: String): Flow<Resource<CategoryWithSEOModel?>> =
        categoryDao.getCategoryWithSEO(categoryId).mapToDomainResource { categoryWithSEO ->
            categoryWithSEO?.toDomain()
        }

    override suspend fun linkCategoryToProduct(
        shopId: String,
        categoryId: String,
        productId: String
    ): Resource<Long> = safeCall {
        categoryDao.insertCategoryProductCrossRef(
            CategoryProductCrossRefEntity(
                shopId = shopId,
                categoryId = categoryId,
                productId = productId
            )
        )
    }


    override suspend fun unlinkCategoryFromProducts(
        categoryId: String,
        productId: String
    ): Resource<Int> = safeCall { categoryDao.deleteCategoryProductCrossRef(categoryId, productId) }


    override suspend fun clearProductsForCategory(categoryId: String): Resource<Int> = safeCall {
        categoryDao.clearProductForCategory(categoryId)
    }

    override suspend fun linkCategoryToWarehouse(
        shopId: String,
        categoryId: String,
        warehouseId: String
    ): Resource<Long> = safeCall {
        categoryDao.insertCategoryWarehouseCrossRef(
            CategoryWarehouseCrossRefEntity(
                shopId = shopId,
                categoryId = categoryId,
                warehouseId = warehouseId
            )
        )
    }

    override suspend fun unlinkCategoryFromWarehouse(
        categoryId: String,
        warehouseId: String
    ): Resource<Int> =
        safeCall { categoryDao.deleteCategoryWarehouseCrossRef(categoryId, warehouseId) }

    override suspend fun clearWarehousesForCategory(categoryId: String): Resource<Int> = safeCall {
        categoryDao.clearWarehousesForCategory(categoryId)
    }

    override suspend fun linkCategoryToDiscount(
        shopId: String,
        categoryId: String,
        discountId: String
    ): Resource<Long> =
        safeCall {
            categoryDao.insertCategoryDiscountCrossRef(
                CategoryDiscountCrossRefEntity(
                    shopId = shopId,
                    categoryId = categoryId,
                    discountId = discountId
                )
            )
        }

    override suspend fun unlinkCategoryFromDiscount(
        categoryId: String,
        discountId: String
    ): Resource<Int> =
        safeCall { categoryDao.deleteCategoryDiscountCrossRef(categoryId, discountId) }

    override suspend fun clearDiscountsForCategory(categoryId: String): Resource<Int> =
        safeCall { categoryDao.clearDiscountsForCategory(categoryId) }

    override suspend fun insertOrUpdateSEO(seo: CategorySeoModel): Resource<Long> = safeCall {
        categoryDao.insertOrUpdateCategorySEO(seo.toEntity())
    }

    override suspend fun deleteSEO(categoryId: String): Resource<Int> =
        safeCall { categoryDao.deleteSEOForCategory(categoryId) }

    override suspend fun syncCategories() {
        try {
            val categoriesSnapshot =
                firebaseFirestoreDataSource.firestore.collection(FirestoreCollections.CATEGORIES)
                    .get()
                    .await()
            val categories =
                categoriesSnapshot.documents.mapNotNull { it.toObject(CategoryEntity::class.java) }
            categoryDao.insertCategories(categories)
        } catch (e: Exception) {
            ElnazLogger.e(TAG, "Error syncing CATEGORIES: ${e.message}")
        }

    }

}