package com.ajijul.elnaz.data

import com.ajijul.elnaz.data.mapper.toAppError
import com.ajijul.elnaz.domain.model.enums.AppError
import com.ajijul.elnaz.domain.model.enums.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T, R> Flow<T>.mapToDomainResource(transform: (T) -> R?): Flow<Resource<R>> {
    return this.map { data ->
        val transformedData = transform(data)
        if (transformedData == null)
            Resource.Error(AppError.Storage.ItemNotFound)
        else
            Resource.Success(transformedData) as Resource<R>
    }.onStart {
        emit(Resource.Loading)
    }.catch { exception ->
        emit(Resource.Error(exception.toAppError()))
    }
}

/**
 * Executes a suspend block safely and wraps the result in Resource.
 */
suspend fun <T> safeCall(action: suspend () -> T?): Resource<T> {
    return try {
        val result = action()
        if (result == null)
            Resource.Error(AppError.Storage.OperationFailed)
        else
            Resource.Success(result)
    } catch (e: Exception) {
        Resource.Error(e.toAppError())
    }
}