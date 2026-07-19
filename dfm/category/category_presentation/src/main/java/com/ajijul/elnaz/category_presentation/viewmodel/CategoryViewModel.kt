package com.ajijul.elnaz.category_presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.core.ui.extensions.UiText
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

    // 1. Grid Stream (Completely isolated)
    private val _listState = MutableStateFlow(CategoryListState())
    val listState: StateFlow<CategoryListState> = _listState.asStateFlow()

    // 2. Form Stream (Bottom Sheet)
    private val _formState = MutableStateFlow(CategoryFormState())
    val formState: StateFlow<CategoryFormState> = _formState.asStateFlow()

    init {
        observeCategories()
    }

    private fun observeCategories() {
        viewModelScope.launch {
            _listState.update { it.copy(isLoading = true) }

            categoryDependencies.getCategoriesUseCase().invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _listState.update { it.copy(categories = resource.data, isLoading = false) }
                    }
                    is Resource.Error -> {
                        _listState.update { it.copy(isLoading = false) }
                    }
                    is Resource.Loading -> {
                        _listState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            // --- GRID EVENTS ---
            is CategoryEvent.OnAddNewCategoryClick -> {
                // Reset form to default
                _formState.value = CategoryFormState(
                    isBottomSheetVisible = true,
                    createdAt = System.currentTimeMillis()
                )
            }
            is CategoryEvent.OnEditCategoryClick -> {
                _formState.value = CategoryFormState(
                    isBottomSheetVisible = true,
                    editingCategoryId = event.category.id,
                    nameInput = event.category.name,
                    descriptionInput = event.category.description,
                    categoryImage = event.category.categoryImage,
                    selectedParentId = event.category.parentId,
                    status = event.category.status,
                    popularityScore = event.category.popularityScore,
                    createdAt = event.category.createdAt // FIXED: Keeps original timestamp
                )
            }
            is CategoryEvent.OnDeleteCategoryClick -> {
                viewModelScope.launch {
                    categoryDependencies.deleteCategoryUseCase().invoke(event.category.id)
                }
            }

            // --- FORM EVENTS ---
            is CategoryEvent.OnNameChanged -> _formState.update { it.copy(nameInput = event.name, nameError = null) }
            is CategoryEvent.OnDescriptionChanged -> _formState.update { it.copy(descriptionInput = event.description) }
            is CategoryEvent.OnImagePicked -> _formState.update { it.copy(categoryImage = event.imageUrl) }
            is CategoryEvent.OnParentSelected -> _formState.update { it.copy(selectedParentId = event.parentId) }
            is CategoryEvent.OnStatusToggled -> _formState.update { it.copy(status = event.status) }
            is CategoryEvent.OnPopularityChanged -> _formState.update { it.copy(popularityScore = event.score) }
            is CategoryEvent.OnDismissBottomSheet -> _formState.update { it.copy(isBottomSheetVisible = false) }
            is CategoryEvent.OnSaveClick -> saveCategory()
        }
    }

    private fun saveCategory() {
        val currentForm = _formState.value

        if (currentForm.nameInput.isBlank()) {
            _formState.update { it.copy(nameError = UiText.DynamicString("Name cannot be empty")) }
            return
        }

        viewModelScope.launch {
            // 1. Show loading state before the suspend call
            _formState.update { it.copy(isSaving = true, nameError = null) }

            // 2. Fetch Shop ID securely from session
            val currentShopId = categoryDependencies.getCurrentUserUseCase().invoke()

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

            // 3. Execute save
            when (val result = categoryDependencies.updateOrInsertCategoryUseCase().invoke(categoryToSave)) {
                is Resource.Success -> {
                    // Reset the form and close the bottom sheet
                    _formState.value = CategoryFormState(isBottomSheetVisible = false)
                }
                is Resource.Error -> {
                    _formState.update {
                        it.copy(isSaving = false, nameError = result.error.asUiText())
                    }
                }
                else -> Unit
            }
        }
    }
}