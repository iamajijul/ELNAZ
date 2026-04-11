package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.WarehousePricingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WarehousePricingDao {
    @Insert
    suspend fun insertWarehousePricing(pricing: WarehousePricingEntity)

    @Insert
    suspend fun insertAllWarehousePricings(pricings: List<WarehousePricingEntity>)

    @Update
    suspend fun updateWarehousePricing(pricing: WarehousePricingEntity)

    @Delete
    suspend fun deleteWarehousePricing(pricing: WarehousePricingEntity)

    @Query("SELECT * FROM warehouse_pricing")
    fun getAllWarehousePricings(): Flow<List<WarehousePricingEntity>>

    @Query("SELECT * FROM warehouse_pricing WHERE warehouseId = :warehouseId")
    fun getPricingsByWarehouseId(warehouseId: Long): Flow<List<WarehousePricingEntity>>

    @Query("SELECT * FROM warehouse_pricing WHERE productId = :productId")
    fun getPricingsByProductId(productId: Long): Flow<List<WarehousePricingEntity>>

    @Query("DELETE FROM warehouse_pricing")
    suspend fun deleteAllWarehousePricings()
}