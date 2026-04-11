package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.PaymentStatus
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "invoice", // Renamed for better accounting terminology
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.RESTRICT // Prevents deleting a customer if they have invoices!
        )
    ],
    indices = [
        Index(value = ["shopId", "customerId"]),
        // Ensures invoice numbers are unique ONLY within a specific shop
        Index(value = ["shopId", "invoiceNumber"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class InvoiceEntity(
    // 1. The SaaS Base
    @PrimaryKey val id: String = UUID.randomUUID().toString(), // Must be a generated UUID
    val shopId: String,
    val customerId: String?, // Nullable for "Walk-in" customers

    // 2. The Core Billing Details
    val invoiceNumber: String, // e.g., "INV-001"
    val orderDate: Long,
    val dueDate: Long?, // Optional: When the payment is expected

    // 3. The Financial Breakdown
    val totalAmount: Double, // The Grand Total
    val totalTaxAmount: Double, // Essential for tax reports
    val totalDiscountAmount: Double,

    // 4. The Khata (Ledger) Tracking
    val amountPaid: Double, // How much the customer has paid so far
    val paymentStatus: PaymentStatus = PaymentStatus.UNPAID, // e.g., "UNPAID", "PARTIAL", "PAID"

    // 5. The Offline Sync Engine
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)

