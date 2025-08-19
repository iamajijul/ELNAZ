package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warehouse")
data class Warehouse(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val managerName: String,
    val totalEmployees: Int,
    val address: String,
    val phone: String
)