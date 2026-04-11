package com.ajijul.elnaz.domain.model

data class CategorySeoModel(
    val id: String,
    val shopId : String,
    val categoryId: String,
    val seoTitle: String?,
    val seoDescription: String?,
    val seoSlug: String?
)
