package com.ajijul.elnaz.category_presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.core.ui.extensions.asUiText
import com.ajijul.elnaz.di.entrypoints.CategoryDependenciesEntryPoint
import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.enums.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryDependencies: CategoryDependenciesEntryPoint
) : ViewModel() {

    // 1. Grid Stream
    private val _listState = MutableStateFlow(CategoryListState())
    val listState: StateFlow<CategoryListState> = _listState.asStateFlow()

    // 2. Form Stream
    private val _formState = MutableStateFlow(CategoryFormState())
    val formState: StateFlow<CategoryFormState> = _formState.asStateFlow()

    init {
        observeCategories()
    }

    private fun observeCategories() {
        viewModelScope.launch {
            categoryDependencies.getCategoriesUseCase().invoke().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _listState.update { it.copy(isLoading = false) }
                    }
                    is Resource.Loading -> {
                        _listState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _listState.update {
                            it.copy(
                                categories = resource.data,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            // --- GRID EVENTS ---
            is CategoryEvent.OnAddNewCategoryClick -> {
                _formState.value = CategoryFormState(isBottomSheetVisible = true)
            }
            is CategoryEvent.OnEditCategoryClick -> {
                _formState.value = CategoryFormState(
                    isBottomSheetVisible = true,
                    editingCategoryId = event.category.id,
                    nameInput = event.category.name,
                    descriptionInput = event.category.description,
                    categoryImage = event.category.categoryImage,
                    selectedParentId = event.category.parentId, // Assumes model parentId is String?
                    status = event.category.status,
                    popularityScore = event.category.popularityScore
                )
            }
            is CategoryEvent.OnDeleteCategoryClick -> {
                viewModelScope.launch { categoryDependencies.deleteCategoryUseCase().invoke(event.category.id) }
            }

            // --- FORM EVENTS ---
            is CategoryEvent.OnNameChanged -> {
                _formState.update { it.copy(nameInput = event.name, nameError = null) }
            }
            is CategoryEvent.OnDescriptionChanged -> {
                _formState.update { it.copy(descriptionInput = event.description) }
            }
            is CategoryEvent.OnImagePicked -> {
                _formState.update { it.copy(categoryImage = event.imageUrl) }
            }
            is CategoryEvent.OnParentSelected -> {
                _formState.update { it.copy(selectedParentId = event.parentId) }
            }
            is CategoryEvent.OnStatusToggled -> {
                _formState.update { it.copy(status = event.status) }
            }
            is CategoryEvent.OnPopularityChanged -> {
                _formState.update { it.copy(popularityScore = event.score) }
            }
            is CategoryEvent.OnDismissBottomSheet -> {
                _formState.update { it.copy(isBottomSheetVisible = false) }
            }
            is CategoryEvent.OnSaveClick -> {
                saveCategory()
            }
        }
    }

    private fun saveCategory() {
        viewModelScope.launch {
            val currentForm = _formState.value

            val categoryToSave = CategoryModel(
                id = currentForm.editingCategoryId,
                shopId = "EXAMPLE",
                name = currentForm.nameInput.trim(),
                createdAt = currentForm.createdAt,
                description = currentForm.descriptionInput.trim(),
                categoryImage = currentForm.categoryImage,
                parentId = currentForm.selectedParentId,
                status = currentForm.status,
                popularityScore = currentForm.popularityScore
            )

            when (val result = categoryDependencies.updateOrInsertCategoryUseCase().invoke(categoryToSave)) {
                is Resource.Success -> {
                    _formState.value = CategoryFormState()
                }
                is Resource.Error -> {
                    _formState.update {
                        it.copy(
                            isSaving = false,
                            nameError = result.error.asUiText()
                        )
                    }
                }
                is Resource.Loading -> _formState.update {
                    it.copy(
                        isSaving = true,
                        nameError = null
                    )
                }

            }
        }
    }
}