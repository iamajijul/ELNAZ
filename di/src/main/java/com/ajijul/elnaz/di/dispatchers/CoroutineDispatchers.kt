package com.ajijul.elnaz.di.dispatchers

import com.ajijul.elnaz.di.annotations.DefaultDispatcher
import com.ajijul.elnaz.di.annotations.IODispatcher
import com.ajijul.elnaz.di.annotations.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchers {

    @Provides
    @MainDispatcher
    @Singleton
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcher
    @Singleton
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IODispatcher
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO


}