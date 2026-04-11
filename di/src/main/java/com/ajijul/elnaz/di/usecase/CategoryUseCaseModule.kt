package com.ajijul.elnaz.di.usecase

import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import com.ajijul.elnaz.domain.usecases.category.DeleteCategoryUseCase
import com.ajijul.elnaz.domain.usecases.category.GetAllCategoriesUseCase
import com.ajijul.elnaz.domain.usecases.category.UpdateOrInsertCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryUseCaseModule {

    @Provides
    @Singleton
    fun provideInsertorUpdateCategoryUseCase(
        repo: CategoryRepository,
    ): UpdateOrInsertCategoryUseCase {
        return UpdateOrInsertCategoryUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetAllCategoryUseCase(
        repo: CategoryRepository,
    ): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideDeleteCategoryUseCase(
        repo: CategoryRepository,
    ): DeleteCategoryUseCase {
        return DeleteCategoryUseCase(repo)
    }

}