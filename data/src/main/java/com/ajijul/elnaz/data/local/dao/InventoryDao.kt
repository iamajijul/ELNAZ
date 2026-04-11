package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.InventoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Insert
    suspend fun insertInventory(inventoryEntity: InventoryEntity)

    @Insert
    suspend fun insertAllInventories(inventories: List<InventoryEntity>)

    @Update
    suspend fun updateInventory(inventoryEntity: InventoryEntity)

    @Delete
    suspend fun deleteInventory(inventoryEntity: InventoryEntity)

    @Query("SELECT * FROM inventory")
    fun getAllInventories(): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory WHERE productId = :productId AND warehouseId = :warehouseId")
    fun getInventoryByProductAndWarehouse(productId: Long, warehouseId: Long): Flow<InventoryEntity?>

    @Query("SELECT SUM(quantity) FROM inventory WHERE productId = :productId")
    fun getTotalQuantityByProduct(productId: Long): Flow<Int?>

    @Query("DELETE FROM inventory")
    suspend fun deleteAllInventories()
}