package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajijul.elnaz.data.local.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertItems(items: List<ItemEntity>)

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE barcode = :barcode")
    suspend fun getItemByBarcode(barcode: String): ItemEntity?

    @Query("SELECT COUNT(*) FROM items")
    fun getItemCount(): Flow<Int>

    @Query("SELECT SUM(quantity * price) FROM items")
    fun getTotalInventoryValue(): Flow<Double?>

    @Query("SELECT * FROM items WHERE quantity <= :threshold")
    fun getLowStockItems(threshold: Int = 0): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE createdAt < :timestamp")
    fun getOldStockItems(timestamp: Long): Flow<List<ItemEntity>>
}