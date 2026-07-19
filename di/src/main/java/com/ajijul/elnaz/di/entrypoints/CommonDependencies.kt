package com.ajijul.elnaz.di.entrypoints

import com.ajijul.elnaz.di.annotations.DefaultDispatcher
import com.ajijul.elnaz.di.annotations.IODispatcher
import com.ajijul.elnaz.di.annotations.MainDispatcher
import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import kotlinx.coroutines.CoroutineDispatcher

interface CommonDependencies {

    @IODispatcher
    fun getIoDispatcher(): CoroutineDispatcher

    @DefaultDispatcher
    fun getDefaultDispatcher(): CoroutineDispatcher

    @MainDispatcher
    fun getMainDispatcher(): CoroutineDispatcher

    fun getCurrentUserUseCase() : CurrentUserUseCase

}