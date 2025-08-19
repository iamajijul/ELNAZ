package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajijul.elnaz.data.local.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertItems(items: List<Product>)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertItem(items: Product)

    @Query("SELECT * FROM product")
    fun getAllItems(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE barcode = :barcode")
    suspend fun getItemByBarcode(barcode: String): Product?

    @Query("SELECT COUNT(*) FROM product")
    fun getItemCount(): Flow<Int>

//   // @Query("SELECT SUM(quantity * price) FROM product")
//    fun getTotalInventoryValue(): Flow<Double?>
//
//  //  @Query("SELECT * FROM product WHERE quantity <= :threshold")
//    fun getLowStockItems(threshold: Int = 0): Flow<List<Product>>
//
//   // @Query("SELECT * FROM product WHERE createdAt < :timestamp")
//    fun getOldStockItems(timestamp: Long): Flow<List<Product>>
}