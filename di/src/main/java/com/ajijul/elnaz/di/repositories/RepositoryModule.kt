package com.ajijul.elnaz.di.repositories

import com.ajijul.elnaz.data.repository.ItemRepositoryImplementation
import com.ajijul.elnaz.domain.repository.ItemRepository
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
    abstract fun bindItemRepositoryWithItemRepositoryImplementation(itemRepositoryImplementation: ItemRepositoryImplementation): ItemRepository

}