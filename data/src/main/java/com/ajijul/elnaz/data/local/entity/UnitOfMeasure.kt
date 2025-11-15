package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("unit_of_measure")
data class UnitOfMeasure(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,             // e.g. "Meter", "Piece", "Kilogram", "Liter"
    val symbol: String,           // e.g. "m", "pcs", "kg", "L"
    val isDecimalAllowed: Boolean // true for meter/weight; false for piece
)
