package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {
    @Insert
    suspend fun insertSupplier(supplier: Supplier)

    @Insert
    suspend fun insertAllSuppliers(suppliers: List<Supplier>)

    @Update
    suspend fun updateSupplier(supplier: Supplier)

    @Delete
    suspend fun deleteSupplier(supplier: Supplier)

    @Query("SELECT * FROM supplier")
    fun getAllSuppliers(): Flow<List<Supplier>>

    @Query("SELECT * FROM supplier WHERE id = :id")
    suspend fun getSupplierById(id: Long): Supplier?

    @Query("SELECT * FROM supplier WHERE name = :name")
    suspend fun getSupplierByName(name: String): Supplier?

    @Query("DELETE FROM supplier")
    suspend fun deleteAllSuppliers()
}