package com.ajijul.elnaz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.ajijul.elnaz.data.local.entity.SupplierEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {
    @Insert
    suspend fun insertSupplier(supplierEntity: SupplierEntity)

    @Insert
    suspend fun insertAllSuppliers(supplierEntities: List<SupplierEntity>)

    @Update
    suspend fun updateSupplier(supplierEntity: SupplierEntity)

    @Delete
    suspend fun deleteSupplier(supplierEntity: SupplierEntity)

    @Query("SELECT * FROM supplier")
    fun getAllSuppliers(): Flow<List<SupplierEntity>>

    @Query("SELECT * FROM supplier WHERE id = :id")
    suspend fun getSupplierById(id: Long): SupplierEntity?

    @Query("SELECT * FROM supplier WHERE name = :name")
    suspend fun getSupplierByName(name: String): SupplierEntity?

    @Query("DELETE FROM supplier")
    suspend fun deleteAllSuppliers()
}