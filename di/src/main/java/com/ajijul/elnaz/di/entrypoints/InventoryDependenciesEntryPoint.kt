package com.ajijul.elnaz.di.entrypoints

import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InventoryDependenciesEntryPoint : CommonDependencies{

    fun getDFMInstaller() : DynamicFeatureInstaller
    fun getCurrentUserUseCase() : CurrentUserUseCase
}