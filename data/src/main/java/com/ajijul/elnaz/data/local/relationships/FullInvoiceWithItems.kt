package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.InvoiceEntity
import com.ajijul.elnaz.data.local.entity.InvoiceItemEntity

data class FullInvoiceWithItems(
    @Embedded val invoice: InvoiceEntity,

    @Relation(
        parentColumn = "id",       // The id in InvoiceEntity
        entityColumn = "invoiceId" // The invoiceId in InvoiceItemEntity
    )
    val items: List<InvoiceItemEntity>
)