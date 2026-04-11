package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.ProductStatus
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "product",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.RESTRICT // Prevent deleting a category if it has products
        ),
        ForeignKey(
            entity = SupplierEntity::class,
            parentColumns = ["id"],
            childColumns = ["supplierId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = UnitOfMeasureEntity::class,
            parentColumns = ["id"],
            childColumns = ["unitId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        // Composite indices ensure SKUs/Barcodes are unique PER SHOP, not globally.
        Index(value = ["shopId", "sku"], unique = true),
        Index(value = ["shopId", "barcode"], unique = true),
        Index(value = ["shopId", "categoryId"]),
        Index(value = ["shopId", "supplierId"]),
        Index(value = ["shopId", "unitId"])
    ]
)
@TypeConverters(Converters::class)
data class ProductEntity(
    // 1. The Machine IDs (UUIDs instead of Longs)
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String, // Critical for SaaS

    // 2. Foreign Keys (Must match the String UUIDs of their parent tables)
    val categoryId: String?,
    val supplierId: String?,
    val unitId: String,

    // 3. Product Details
    val name: String,
    val sku: String,
    val barcode: String,
    val status: ProductStatus = ProductStatus.ACTIVE,

    // 4. Vyapar-Style Pricing & Tax
    val buyPrice: Double,
    val salePrice: Double,
    val mrp: Double,
    val taxRate: Double,
    val isTaxInclusive: Boolean,
    val hsnSacCode: String? = null,

    // 5. Offline Sync Engine
    val firstAddedOn: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false // Soft delete for offline sync
)