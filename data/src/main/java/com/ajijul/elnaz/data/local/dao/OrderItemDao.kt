package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Insert
    suspend fun insertOrderItem(orderItem: OrderItem)

    @Insert
    suspend fun insertAllOrderItems(orderItems: List<OrderItem>)

    @Update
    suspend fun updateOrderItem(orderItem: OrderItem)

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)

    @Query("SELECT * FROM order_item")
    fun getAllOrderItems(): Flow<List<OrderItem>>

    @Query("SELECT * FROM order_item WHERE orderId = :orderId")
    fun getOrderItemsByOrderId(orderId: Long): Flow<List<OrderItem>>

    @Query("SELECT * FROM order_item WHERE productId = :productId")
    fun getOrderItemsByProductId(productId: Long): Flow<List<OrderItem>>

    @Query("DELETE FROM order_item")
    suspend fun deleteAllOrderItems()
}