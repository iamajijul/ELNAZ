package com.ajijul.elnaz.category_presentation.viewmodel

import com.ajijul.elnaz.domain.model.CategoryModel

data class CategoryListState(
    val categories: List<CategoryModel> = emptyList(),
    val isLoading: Boolean = true
)