package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.WarehousePricing
import kotlinx.coroutines.flow.Flow

@Dao
interface WarehousePricingDao {
    @Insert
    suspend fun insertWarehousePricing(pricing: WarehousePricing)

    @Insert
    suspend fun insertAllWarehousePricings(pricings: List<WarehousePricing>)

    @Update
    suspend fun updateWarehousePricing(pricing: WarehousePricing)

    @Delete
    suspend fun deleteWarehousePricing(pricing: WarehousePricing)

    @Query("SELECT * FROM warehouse_pricing")
    fun getAllWarehousePricings(): Flow<List<WarehousePricing>>

    @Query("SELECT * FROM warehouse_pricing WHERE warehouseId = :warehouseId")
    fun getPricingsByWarehouseId(warehouseId: Long): Flow<List<WarehousePricing>>

    @Query("SELECT * FROM warehouse_pricing WHERE productId = :productId")
    fun getPricingsByProductId(productId: Long): Flow<List<WarehousePricing>>

    @Query("DELETE FROM warehouse_pricing")
    suspend fun deleteAllWarehousePricings()
}