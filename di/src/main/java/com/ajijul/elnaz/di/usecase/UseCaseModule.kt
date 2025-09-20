package com.ajijul.elnaz.di.usecase

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.usecases.CurrentUserUseCase
import com.ajijul.elnaz.domain.auth.usecases.LoginUseCase
import com.ajijul.elnaz.domain.auth.usecases.LogoutUseCase
import com.ajijul.elnaz.domain.auth.usecases.RegisterUseCase
import com.ajijul.elnaz.domain.repository.ProductRepository
import com.ajijul.elnaz.domain.usecases.AddItemUseCase
import com.ajijul.elnaz.domain.usecases.CheckStockAlertsUseCase
import com.ajijul.elnaz.domain.usecases.GetProductsUseCase
import com.ajijul.elnaz.domain.user.UserPreferenceRepository
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