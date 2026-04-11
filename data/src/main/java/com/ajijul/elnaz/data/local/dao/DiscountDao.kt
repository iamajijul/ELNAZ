package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.DiscountEntity
import com.ajijul.elnaz.domain.model.enums.DiscountStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscountDao {
    @Insert
    suspend fun insertDiscount(discountEntity: DiscountEntity)

    @Insert
    suspend fun insertAllDiscounts(discountEntities: List<DiscountEntity>)

    @Update
    suspend fun updateDiscount(discountEntity: DiscountEntity)

    @Delete
    suspend fun deleteDiscount(discountEntity: DiscountEntity)

    @Query("SELECT * FROM discount")
    fun getAllDiscounts(): Flow<List<DiscountEntity>>

    @Query("SELECT * FROM discount WHERE id = :id")
    suspend fun getDiscountById(id: Long): DiscountEntity?

    @Query("SELECT * FROM discount WHERE status = :status ORDER BY priority DESC")
    fun getDiscountsByStatus(status: DiscountStatus): Flow<List<DiscountEntity>>

    @Query("SELECT * FROM discount WHERE status = 'ACTIVE' ORDER BY priority DESC")
    fun getActiveDiscounts(): Flow<List<DiscountEntity>>

    @Query("DELETE FROM discount")
    suspend fun deleteAllDiscounts()
}