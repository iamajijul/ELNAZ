package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRefEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryProductCrossRefEntity
import com.ajijul.elnaz.data.local.entity.category.CategorySEOEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRefEntity
import com.ajijul.elnaz.data.local.relationships.CategoryWithDiscount
import com.ajijul.elnaz.data.local.relationships.CategoryWithProducts
import com.ajijul.elnaz.data.local.relationships.CategoryWithSEO
import com.ajijul.elnaz.data.local.relationships.CategoryWithWarehouse
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    // ----- Category CRUD -----

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity): Long

    // Added for Sync: Insert a whole list from Firebase at once
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>): List<Long>

    @Update
    suspend fun updateCategory(categoryEntity: CategoryEntity): Int

    @Delete
    suspend fun deleteCategory(categoryEntity: CategoryEntity): Int

    @Query("DELETE FROM category WHERE id = :id")
    suspend fun deleteCategoryById(id: String): Int


    @Query("SELECT * FROM category ORDER BY popularityScore DESC, name ASC")
    fun getAllCategoriesFlow(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategoryById(id: String): Flow<CategoryEntity?>

    // ----- Relations (Transaction ensures consistency) -----

    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithProducts(categoryId: String): Flow<CategoryWithProducts?>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithWarehouses(categoryId: String): Flow<CategoryWithWarehouse?>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithDiscounts(categoryId: String): Flow<CategoryWithDiscount?>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithSEO(categoryId: String): Flow<CategoryWithSEO?>

    // ----- CrossRef operations (String IDs) -----

    // 1. Warehouses
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryWarehouseCrossRef(ref: CategoryWarehouseCrossRefEntity): Long?

    @Query("DELETE FROM category_warehouse_cross_ref WHERE categoryId = :categoryId AND warehouseId = :warehouseId")
    suspend fun deleteCategoryWarehouseCrossRef(categoryId: String, warehouseId: String): Int?

    @Query("DELETE FROM category_warehouse_cross_ref WHERE categoryId = :categoryId")
    suspend fun clearWarehousesForCategory(categoryId: String): Int?

    // 2. Discounts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryDiscountCrossRef(ref: CategoryDiscountCrossRefEntity): Long?

    @Query("DELETE FROM category_discount_cross_ref WHERE categoryId = :categoryId AND discountId = :discountId")
    suspend fun deleteCategoryDiscountCrossRef(categoryId: String, discountId: String): Int?

    @Query("DELETE FROM category_discount_cross_ref WHERE categoryId = :categoryId")
    suspend fun clearDiscountsForCategory(categoryId: String): Int?

    // 3. Products (The one we discussed!)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryProductCrossRef(prodRef: CategoryProductCrossRefEntity): Long

    // Added Bulk Insert for Syncing CrossRefs from Firebase
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryProductCrossRefs(prodRefs: List<CategoryProductCrossRefEntity>)

    @Query("DELETE FROM category_product_cross_ref WHERE categoryId = :categoryId AND productId = :productId")
    suspend fun deleteCategoryProductCrossRef(categoryId: String, productId: String): Int?

    @Query("DELETE FROM category_product_cross_ref WHERE categoryId = :categoryId")
    suspend fun clearProductForCategory(categoryId: String): Int?

    // ----- SEO -----
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCategorySEO(seo: CategorySEOEntity): Long?

    @Query("DELETE FROM category_seo WHERE categoryId = :categoryId")
    suspend fun deleteSEOForCategory(categoryId: String): Int?

    // Sub-Categories (String ID)
    @Query("SELECT * FROM category WHERE parentId = :parentId")
    fun getAllSubCategories(parentId: String): Flow<List<CategoryEntity>>
}