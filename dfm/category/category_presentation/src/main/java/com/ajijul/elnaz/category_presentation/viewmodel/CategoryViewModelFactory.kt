package com.ajijul.elnaz.category_presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajijul.elnaz.di.entrypoints.CategoryDependenciesEntryPoint

class CategoryViewModelFactory(
    val categoryDependencies: CategoryDependenciesEntryPoint
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(categoryDependencies) as T

    }
}