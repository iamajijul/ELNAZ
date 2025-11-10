package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "order",
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["id"],
        childColumns = ["customerId"],
        onUpdate = ForeignKey.CASCADE
    )],
    indices = [Index("customerId")]
)
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val customerId: Long?,
    val orderDate: Long,
    val totalAmount: Double
)

