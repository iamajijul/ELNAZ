package com.ajijul.elnaz.di.usecase

import com.ajijul.elnaz.domain.repository.ItemRepository
import com.ajijul.elnaz.domain.usecases.AddItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddItemUseCase(itemRepository: ItemRepository): AddItemUseCase {
        return AddItemUseCase(itemRepository)
    }
}