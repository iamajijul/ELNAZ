package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Transaction
import com.ajijul.elnaz.data.local.entity.Warehouse
import com.ajijul.elnaz.data.local.relationships.WarehouseWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface WarehouseDao {
    @Insert
    suspend fun insertWarehouse(warehouse: Warehouse)

    @Insert
    suspend fun insertAllWarehouses(warehouses: List<Warehouse>)

    @Update
    suspend fun updateWarehouse(warehouse: Warehouse)

    @Delete
    suspend fun deleteWarehouse(warehouse: Warehouse)

    @Query("SELECT * FROM warehouse")
    fun getAllWarehouses(): Flow<List<Warehouse>>

    @Query("SELECT * FROM warehouse WHERE id = :id")
    suspend fun getWarehouseById(id: Long): Warehouse?

    @Query("SELECT * FROM warehouse WHERE name = :name")
    suspend fun getWarehouseByName(name: String): Warehouse?

    @Query("DELETE FROM warehouse")
    suspend fun deleteAllWarehouses()

    @Transaction
    @Query("SELECT * FROM warehouse WHERE id = :warehouseId")
    fun getWarehouseWithCategories(warehouseId: Long): Flow<WarehouseWithCategories?>

}