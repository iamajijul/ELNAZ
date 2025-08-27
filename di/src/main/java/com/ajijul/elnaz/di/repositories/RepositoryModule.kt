package com.ajijul.elnaz.di.repositories

import com.ajijul.elnaz.data.repository.auth.AuthRepositoryImplementation
import com.ajijul.elnaz.data.repository.product.ProductRepositoryImplementation
import com.ajijul.elnaz.data.repository.user.UserPreferenceRepositoryImpl
import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.repository.ProductRepository
import com.ajijul.elnaz.domain.user.UserPreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepositoryWithImplementation(
        productRepositoryImplementation: ProductRepositoryImplementation
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepositoryWithImplementation(
        authRepositoryImplementation: AuthRepositoryImplementation
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferenceRepositoryWithImplementation(
        userPreferenceRepositoryImpl: UserPreferenceRepositoryImpl
    ): UserPreferenceRepository

}