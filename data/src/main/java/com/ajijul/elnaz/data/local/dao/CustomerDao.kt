package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert
    suspend fun insertCustomer(customerEntity: CustomerEntity)

    @Insert
    suspend fun insertAllCustomers(customerEntities: List<CustomerEntity>)

    @Update
    suspend fun updateCustomer(customerEntity: CustomerEntity)

    @Delete
    suspend fun deleteCustomer(customerEntity: CustomerEntity)

    @Query("SELECT * FROM customer")
    fun getAllCustomers(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customer WHERE id = :id")
    suspend fun getCustomerById(id: Long): CustomerEntity?

    @Query("SELECT * FROM customer WHERE email = :email")
    suspend fun getCustomerByEmail(email: String): CustomerEntity?

    @Query("DELETE FROM customer")
    suspend fun deleteAllCustomers()
}