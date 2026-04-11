package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Transaction
import com.ajijul.elnaz.data.local.entity.WarehouseEntity
import com.ajijul.elnaz.data.local.relationships.WarehouseWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface WarehouseDao {
    @Insert
    suspend fun insertWarehouse(warehouseEntity: WarehouseEntity)

    @Insert
    suspend fun insertAllWarehouses(warehouseEntities: List<WarehouseEntity>)

    @Update
    suspend fun updateWarehouse(warehouseEntity: WarehouseEntity)

    @Delete
    suspend fun deleteWarehouse(warehouseEntity: WarehouseEntity)

    @Query("SELECT * FROM warehouse")
    fun getAllWarehouses(): Flow<List<WarehouseEntity>>

    @Query("SELECT * FROM warehouse WHERE id = :id")
    suspend fun getWarehouseById(id: Long): WarehouseEntity?

    @Query("SELECT * FROM warehouse WHERE name = :name")
    suspend fun getWarehouseByName(name: String): WarehouseEntity?

    @Query("DELETE FROM warehouse")
    suspend fun deleteAllWarehouses()

    @Transaction
    @Query("SELECT * FROM warehouse WHERE id = :warehouseId")
    fun getWarehouseWithCategories(warehouseId: Long): Flow<WarehouseWithCategories?>

}