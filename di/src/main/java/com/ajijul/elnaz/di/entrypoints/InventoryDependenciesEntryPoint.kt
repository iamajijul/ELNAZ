package com.ajijul.elnaz.di.entrypoints

import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InventoryDependenciesEntryPoint : CommonDependencies{
    fun getCurrentUserUseCase() : CurrentUserUseCase
}