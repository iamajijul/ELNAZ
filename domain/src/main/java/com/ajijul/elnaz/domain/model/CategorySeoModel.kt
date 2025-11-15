package com.ajijul.elnaz.domain.model

data class CategorySeoModel(
    val id: Long = 0,
    val categoryId: Long,
    val seoTitle: String?,
    val seoDescription: String?,
    val seoSlug: String?
)
