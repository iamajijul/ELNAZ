package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: Order)

    @Insert
    suspend fun insertAllOrders(orders: List<Order>)

    @Update
    suspend fun updateOrder(order: Order)

    @Delete
    suspend fun deleteOrder(order: Order)

    @Query("SELECT * FROM 'order'")
    fun getAllOrders(): Flow<List<Order>>

    @Query("SELECT * FROM 'order' WHERE id = :id")
    suspend fun getOrderById(id: Long): Order?

    @Query("SELECT * FROM 'order' WHERE customerId = :customerId")
    fun getOrdersByCustomerId(customerId: Long?): Flow<List<Order>>

    @Query("""
        SELECT c.id AS customerId, c.name, COUNT(o.id) AS orderCount, SUM(o.totalAmount) AS totalSpent
        FROM customer c
        LEFT JOIN 'order' o ON c.id = o.customerId
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

    @Query("DELETE FROM 'order'")
    suspend fun deleteAllOrders()
}