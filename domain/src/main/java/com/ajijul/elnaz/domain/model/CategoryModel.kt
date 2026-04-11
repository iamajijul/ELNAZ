package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.CategoryStatus

data class CategoryModel(
    val id: String,
    val shopId : String,
    val name: String,
    val description: String,
    val categoryImage: String,
    val parentId: String? = null,
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    val createdAt: Long = System.currentTimeMillis(),
    val popularityScore: Int = 0
)
