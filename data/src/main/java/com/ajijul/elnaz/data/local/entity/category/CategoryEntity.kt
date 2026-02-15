package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.CategoryStatus
import java.util.UUID

@Entity(
    tableName = "category",
    indices = [Index(value = ["name"], unique = true)]
)
@TypeConverters(Converters::class)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String = "",
    val categoryImage: String = "",
    val parentId: Long? = null,
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    val createdAt: Long = System.currentTimeMillis(),
    val popularityScore: Int = 0
)