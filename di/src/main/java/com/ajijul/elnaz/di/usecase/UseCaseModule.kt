package com.ajijul.elnaz.di.usecase

import com.ajijul.elnaz.domain.repository.ProductRepository
import com.ajijul.elnaz.domain.usecases.AddItemUseCase
import com.ajijul.elnaz.domain.usecases.CheckStockAlertsUseCase
import com.ajijul.elnaz.domain.usecases.GetProductsUseCase
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
    fun provideAddItemUseCase(productRepository: ProductRepository): AddItemUseCase {
        return AddItemUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetItemUseCase(productRepository: ProductRepository): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideCheckStockAlertsUseCase(productRepository: ProductRepository): CheckStockAlertsUseCase {
        return CheckStockAlertsUseCase(productRepository)
    }
}