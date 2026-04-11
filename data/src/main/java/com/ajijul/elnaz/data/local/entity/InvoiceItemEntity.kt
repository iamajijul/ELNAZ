package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "invoice_item",
    foreignKeys = [
        // Link to the Parent Invoice
        ForeignKey(
            entity = InvoiceEntity::class,
            parentColumns = ["id"],
            childColumns = ["invoiceId"],
            onDelete = ForeignKey.CASCADE, // If the invoice is deleted, delete all its items!
            onUpdate = ForeignKey.CASCADE
        ),
        // Link to the Product Catalog
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.RESTRICT, // Don't let the shop delete a product if it's on an old invoice!
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["shopId", "invoiceId"]),
        Index(value = ["shopId", "productId"])
    ]
)
data class InvoiceItemEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(), // UUID for this specific row
    val shopId: String,

    val invoiceId: String, // Which bill does this belong to?
    val productId: String, // What did they buy?

    // The Line Item Math
    val quantity: Double,
    val pricePerUnit: Double, // The price AT THE TIME OF SALE (Do not rely on the Product table, prices change!)
    val taxAmount: Double, // Tax for this specific row
    val discountAmount: Double, // Discount for this specific row
    val finalRowTotal: Double, // (quantity * pricePerUnit) + tax - discount

    // The Offline Sync Engine
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)
