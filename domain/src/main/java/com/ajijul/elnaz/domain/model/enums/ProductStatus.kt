package com.ajijul.elnaz.domain.model.enums

enum class ProductStatus {
    ACTIVE,        // visible, available for sale
    OUT_OF_STOCK,  // no quantity left in inventory
    DISCONTINUED,  // permanently stopped selling
    DRAFT,         // being prepared, not published yet
    ARCHIVED       // old/legacy product, kept for reports
}