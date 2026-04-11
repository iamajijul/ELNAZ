package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.InvoiceItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Insert
    suspend fun insertOrderItem(orderItemEntity: InvoiceItemEntity)

    @Insert
    suspend fun insertAllOrderItems(orderItemEntities: List<InvoiceItemEntity>)

    @Update
    suspend fun updateOrderItem(orderItemEntity: InvoiceItemEntity)

    @Delete
    suspend fun deleteOrderItem(orderItemEntity: InvoiceItemEntity)

    @Query("SELECT * FROM invoice_item")
    fun getAllOrderItems(): Flow<List<InvoiceItemEntity>>

    @Query("SELECT * FROM invoice_item WHERE invoiceId = :invoiceId")
    fun getOrderItemsByOrderId(invoiceId: Long): Flow<List<InvoiceItemEntity>>

    @Query("SELECT * FROM invoice_item WHERE productId = :productId")
    fun getOrderItemsByProductId(productId: Long): Flow<List<InvoiceItemEntity>>

    @Query("DELETE FROM invoice_item")
    suspend fun deleteAllOrderItems()
}