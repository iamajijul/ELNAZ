package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.CategoryStatus

@Entity(
    tableName = "category",
    indices = [Index(value = ["name"], unique = true)]
)
@TypeConverters(Converters::class)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val parentId: Long? = null,
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    val createdAt: Long,
    val popularityScore: Int = 0
)