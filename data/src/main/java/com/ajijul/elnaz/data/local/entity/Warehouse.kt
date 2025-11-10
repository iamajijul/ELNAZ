package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.WarehouseStatus

@Entity(
    tableName = "warehouse", indices = [Index("name"),
        Index(value = ["code"], unique = true)]
)
@TypeConverters(Converters::class)

data class Warehouse(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val code: String, // Human-readable identifier, e.g., "DELHI01"
    val name: String,
    val location: String,
    val contactNumber: String?,
    val managerName: String?,
    val status: WarehouseStatus = WarehouseStatus.ACTIVE,
    val createdAt: Long
)