package com.ajijul.elnaz.di.repositories

import com.ajijul.elnaz.data.repository.ProductRepositoryImplementation
import com.ajijul.elnaz.domain.repository.ProductRepository
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
    abstract fun bindItemRepositoryWithItemRepositoryImplementation(itemRepositoryImplementation: ProductRepositoryImplementation): ProductRepository

}