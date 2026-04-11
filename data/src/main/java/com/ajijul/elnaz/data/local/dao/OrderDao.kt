package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.InvoiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: InvoiceEntity)

    @Insert
    suspend fun insertAllOrders(orders: List<InvoiceEntity>)

    @Update
    suspend fun updateOrder(order: InvoiceEntity)

    @Delete
    suspend fun deleteOrder(order: InvoiceEntity)

    @Query("SELECT * FROM 'invoice'")
    fun getAllOrders(): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM 'invoice' WHERE id = :id")
    suspend fun getOrderById(id: Long): InvoiceEntity?

    @Query("SELECT * FROM 'invoice' WHERE customerId = :customerId")
    fun getOrdersByCustomerId(customerId: Long?): Flow<List<InvoiceEntity>>

    @Query("""
        SELECT c.id AS customerId, c.name, COUNT(o.id) AS orderCount, SUM(o.totalAmount) AS totalSpent
        FROM customer c
        LEFT JOIN 'invoice' o ON c.id = o.customerId
        GROUP BY c.id, c.name
        ORDER BY orderCount DESC, totalSpent DESC
    """)
    fun getCustomerSpendingStats(): Flow<List<CustomerSpending>>

    data class CustomerSpending(
        val customerId: Long,
        val name: String,
        val orderCount: Int,
        val totalSpent: Double
    )

    @Query("DELETE FROM 'invoice'")
    suspend fun deleteAllOrders()
}