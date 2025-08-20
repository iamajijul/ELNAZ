package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.Inventory
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Insert
    suspend fun insertInventory(inventory: Inventory)

    @Insert
    suspend fun insertAllInventories(inventories: List<Inventory>)

    @Update
    suspend fun updateInventory(inventory: Inventory)

    @Delete
    suspend fun deleteInventory(inventory: Inventory)

    @Query("SELECT * FROM inventory")
    fun getAllInventories(): Flow<List<Inventory>>

    @Query("SELECT * FROM inventory WHERE productId = :productId AND warehouseId = :warehouseId")
    fun getInventoryByProductAndWarehouse(productId: Long, warehouseId: Long): Flow<Inventory?>

    @Query("SELECT SUM(quantity) FROM inventory WHERE productId = :productId")
    fun getTotalQuantityByProduct(productId: Long): Flow<Int?>

    @Query("DELETE FROM inventory")
    suspend fun deleteAllInventories()
}