package com.ajijul.elnaz.di.usecase

import com.ajijul.elnaz.domain.repository.ItemRepository
import com.ajijul.elnaz.domain.usecases.AddItemUseCase
import com.ajijul.elnaz.domain.usecases.CheckStockAlertsUseCase
import com.ajijul.elnaz.domain.usecases.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAddItemUseCase(itemRepository: ItemRepository): AddItemUseCase {
        return AddItemUseCase(itemRepository)
    }

    @Provides
    @Singleton
    fun provideGetItemUseCase(itemRepository: ItemRepository): GetItemsUseCase {
        return GetItemsUseCase(itemRepository)
    }

    @Provides
    @Singleton
    fun provideCheckStockAlertsUseCase(itemRepository: ItemRepository): CheckStockAlertsUseCase {
        return CheckStockAlertsUseCase(itemRepository)
    }
}