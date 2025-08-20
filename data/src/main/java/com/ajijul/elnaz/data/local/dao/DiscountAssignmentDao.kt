package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.DiscountAssignment
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscountAssignmentDao {
    @Insert
    suspend fun insertDiscountAssignment(assignment: DiscountAssignment)

    @Insert
    suspend fun insertAllDiscountAssignments(assignments: List<DiscountAssignment>)

    @Update
    suspend fun updateDiscountAssignment(assignment: DiscountAssignment)

    @Delete
    suspend fun deleteDiscountAssignment(assignment: DiscountAssignment)

    @Query("SELECT * FROM discount_assignment")
    fun getAllDiscountAssignments(): Flow<List<DiscountAssignment>>

    @Query("SELECT * FROM discount_assignment WHERE discountId = :discountId")
    fun getAssignmentsByDiscountId(discountId: Long): Flow<List<DiscountAssignment>>

    @Query("SELECT * FROM discount_assignment WHERE productId = :productId")
    fun getAssignmentsByProductId(productId: Long?): Flow<List<DiscountAssignment>>

    @Query("SELECT * FROM discount_assignment WHERE categoryId = :categoryId")
    fun getAssignmentsByCategoryId(categoryId: Long?): Flow<List<DiscountAssignment>>

    @Query("SELECT * FROM discount_assignment WHERE warehouseId = :warehouseId")
    fun getAssignmentsByWarehouseId(warehouseId: Long?): Flow<List<DiscountAssignment>>

    @Query("DELETE FROM discount_assignment")
    suspend fun deleteAllDiscountAssignments()
}