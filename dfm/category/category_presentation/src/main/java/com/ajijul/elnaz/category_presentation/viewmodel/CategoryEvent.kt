package com.ajijul.elnaz.category_presentation.viewmodel

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.enums.CategoryStatus

sealed interface CategoryEvent{

    // --- Grid Actions ---
    object OnAddNewCategoryClick : CategoryEvent
    data class OnEditCategoryClick(val category: CategoryModel) : CategoryEvent
    data class OnDeleteCategoryClick(val category: CategoryModel) : CategoryEvent

    // --- Form Actions ---
    data class OnNameChanged(val name: String) : CategoryEvent
    data class OnDescriptionChanged(val description: String) : CategoryEvent
    data class OnImagePicked(val imageUrl: String) : CategoryEvent
    data class OnParentSelected(val parentId: String?) : CategoryEvent
    data class OnStatusToggled(val status: CategoryStatus) : CategoryEvent
    data class OnPopularityChanged(val score: Int) : CategoryEvent

    // --- Submission Actions ---
    object OnDismissBottomSheet : CategoryEvent
    object OnSaveClick : CategoryEvent

}