package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.Discount
import com.ajijul.elnaz.domain.model.enums.DiscountStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscountDao {
    @Insert
    suspend fun insertDiscount(discount: Discount)

    @Insert
    suspend fun insertAllDiscounts(discounts: List<Discount>)

    @Update
    suspend fun updateDiscount(discount: Discount)

    @Delete
    suspend fun deleteDiscount(discount: Discount)

    @Query("SELECT * FROM discount")
    fun getAllDiscounts(): Flow<List<Discount>>

    @Query("SELECT * FROM discount WHERE id = :id")
    suspend fun getDiscountById(id: Long): Discount?

    @Query("SELECT * FROM discount WHERE status = :status ORDER BY priority DESC")
    fun getDiscountsByStatus(status: DiscountStatus): Flow<List<Discount>>

    @Query("SELECT * FROM discount WHERE status = 'ACTIVE' ORDER BY priority DESC")
    fun getActiveDiscounts(): Flow<List<Discount>>

    @Query("DELETE FROM discount")
    suspend fun deleteAllDiscounts()
}