package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SupplierStatus

@Entity(
    tableName = "supplier",
    indices = [Index(value = ["name"], unique = true), Index(value = ["supplierCode"], unique = true)]
)
@TypeConverters(Converters::class)
data class Supplier(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val supplierCode: String,
    val name: String,
    val email: String?,
    val phone: String?,
    val address: String?,
    val gstNumber: String?,
    val status: SupplierStatus = SupplierStatus.ACTIVE,
    val createdAt: Long = System.currentTimeMillis()
)
