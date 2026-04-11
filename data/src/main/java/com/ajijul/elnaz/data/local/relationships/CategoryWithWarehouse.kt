package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.WarehouseEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRefEntity

data class CategoryWithWarehouse(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(CategoryWarehouseCrossRefEntity::class,
            parentColumn = "categoryId",
            entityColumn = "warehouseId")
    ) val warehouseEntities: List<WarehouseEntity>
)
