package com.ajijul.elnaz.di.entrypoints

import com.ajijul.elnaz.domain.usecases.category.DeleteCategoryUseCase
import com.ajijul.elnaz.domain.usecases.category.GetAllCategoriesUseCase
import com.ajijul.elnaz.domain.usecases.category.UpdateOrInsertCategoryUseCase
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CategoryDependenciesEntryPoint : CommonDependencies{

    fun getDFMInstaller() : DynamicFeatureInstaller
    fun getCategoriesUseCase() : GetAllCategoriesUseCase
    fun updateOrInsertCategoryUseCase() : UpdateOrInsertCategoryUseCase
    fun deleteCategoryUseCase() : DeleteCategoryUseCase

}