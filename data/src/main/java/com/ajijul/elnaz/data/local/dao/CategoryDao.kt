package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.ajijul.elnaz.data.local.entity.category.Category
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRef
import com.ajijul.elnaz.data.local.entity.category.CategoryProductCrossRef
import com.ajijul.elnaz.data.local.entity.category.CategorySEO
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRef
import com.ajijul.elnaz.data.local.relationships.CategoryWithDiscount
import com.ajijul.elnaz.data.local.relationships.CategoryWithProducts
import com.ajijul.elnaz.data.local.relationships.CategoryWithSEO
import com.ajijul.elnaz.data.local.relationships.CategoryWithWarehouse
import com.ajijul.elnaz.data.local.relationships.WarehouseWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    // ----- Category CRUD -----
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("DELETE FROM category WHERE id = :id")
    suspend fun deleteCategoryById(id: Long)

    @Query("SELECT * FROM category ORDER BY name ASC")
    fun getAllCategoriesFlow(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Long): Category?

    // ----- Relations (Transaction ensures consistency) -----
    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithProducts(categoryId: Long): Flow<CategoryWithProducts?>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithWarehouses(categoryId: Long): Flow<CategoryWithWarehouse?>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithDiscounts(categoryId: Long): Flow<CategoryWithDiscount?>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :categoryId")
    suspend fun getCategoryWithSEO(categoryId: Long): CategoryWithSEO?

    // ----- CrossRef operations -----
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryWarehouseCrossRef(ref: CategoryWarehouseCrossRef)

    @Query("DELETE FROM category_warehouse_cross_ref WHERE categoryId = :categoryId AND warehouseId = :warehouseId")
    suspend fun deleteCategoryWarehouseCrossRef(categoryId: Long, warehouseId: Long)

    @Query("DELETE FROM category_warehouse_cross_ref WHERE categoryId = :categoryId")
    suspend fun clearWarehousesForCategory(categoryId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryDiscountCrossRef(ref: CategoryDiscountCrossRef)

    @Query("DELETE FROM category_discount_cross_ref WHERE categoryId = :categoryId AND discountId = :discountId")
    suspend fun deleteCategoryDiscountCrossRef(categoryId: Long, discountId: Long)

    @Query("DELETE FROM category_discount_cross_ref WHERE categoryId = :categoryId")
    suspend fun clearDiscountsForCategory(categoryId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryProductCrossRef(prodRef: CategoryProductCrossRef)

    @Query("DELETE FROM category_product_cross_ref WHERE categoryId = :categoryId AND productId = :productId")
    suspend fun deleteCategoryProductCrossRef(categoryId: Long, productId: Long)

    @Query("DELETE FROM category_product_cross_ref WHERE categoryId = :categoryId")
    suspend fun clearProductForCategory(categoryId: Long)

    // ----- SEO -----
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCategorySEO(seo: CategorySEO): Long

    @Query("DELETE FROM category_seo WHERE categoryId = :categoryId")
    suspend fun deleteSEOForCategory(categoryId: Long)

    @Query("SELECT * FROM category WHERE parentId = :parentId")
    fun getAllSubCategories(parentId: Long): Flow<List<Category>>
}

