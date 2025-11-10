package com.ajijul.elnaz.domain.model

data class CategoryModel(
    val id: Long = 0,
    val name: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)
