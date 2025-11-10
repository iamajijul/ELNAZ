package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.Warehouse
import com.ajijul.elnaz.data.local.entity.category.Category
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRef

data class CategoryWithWarehouse(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(CategoryWarehouseCrossRef::class,
            parentColumn = "categoryId",
            entityColumn = "warehouseId")
    ) val warehouses: List<Warehouse>
)
