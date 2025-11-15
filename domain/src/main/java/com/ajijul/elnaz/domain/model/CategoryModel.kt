package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.CategoryStatus

data class CategoryModel(
    val id: Long = 0,
    val name: String,
    val description: String,
    val parentId: Long? = null,
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    val createdAt: Long = System.currentTimeMillis(),
    val popularityScore: Int = 0
)
