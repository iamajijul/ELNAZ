package com.ajijul.elnaz.di.usecase

import com.ajijul.elnaz.domain.repository.auth.AuthRepository
import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import com.ajijul.elnaz.domain.usecases.auth.LoginUseCase
import com.ajijul.elnaz.domain.usecases.auth.LogoutUseCase
import com.ajijul.elnaz.domain.usecases.auth.RegisterUseCase
import com.ajijul.elnaz.domain.repository.product.ProductRepository
import com.ajijul.elnaz.domain.usecases.product.AddItemUseCase
import com.ajijul.elnaz.domain.usecases.product.CheckStockAlertsUseCase
import com.ajijul.elnaz.domain.usecases.product.GetProductsUseCase
import com.ajijul.elnaz.domain.repository.user.UserPreferenceRepository
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
    fun provideRegisterUseCase(
        repo: AuthRepository,
        userPreferenceRepository: UserPreferenceRepository
    ): RegisterUseCase {
        return RegisterUseCase(repo, userPreferenceRepository)
    }
    
    @Provides
    @Singleton
    fun provideCurrentUserUseCase(
        userPreferenceRepository: UserPreferenceRepository
    ): CurrentUserUseCase {
        return CurrentUserUseCase(userPreferenceRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repo: AuthRepository,
        userPreferenceRepository: UserPreferenceRepository
    ): LoginUseCase {
        return LoginUseCase(repo, userPreferenceRepository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        repo: AuthRepository,
        userPreferenceRepository: UserPreferenceRepository
    ): LogoutUseCase {
        return LogoutUseCase(repo, userPreferenceRepository)
    }

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