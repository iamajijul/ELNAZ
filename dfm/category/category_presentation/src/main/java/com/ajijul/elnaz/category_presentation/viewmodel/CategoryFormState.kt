package com.ajijul.elnaz.category_presentation.viewmodel

import com.ajijul.elnaz.core.ui.extensions.UiText
import com.ajijul.elnaz.domain.model.enums.CategoryStatus

data class CategoryFormState(
    val isBottomSheetVisible: Boolean = false,
    val isSaving: Boolean = false,

    //For Editing any category
    val editingCategoryId: String = "",
    val createdAt: Long = 0L,

    val nameInput: String = "",
    val nameError: UiText? = null,

    val descriptionInput: String = "",
    val categoryImage: String = "",

    val selectedParentId: String? = null, // Using String to match CategoryModel ID
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    val popularityScore: Int = 0
)