package com.ajijul.elnaz.domain.model.enums

sealed class AppError {

    //Login or Registration Related
    object InvalidEmail : AppError()
    object InvalidPassword : AppError()
    object InvalidCredentials : AppError()

    // Common network-related errors
    object NetworkError : AppError()
    object TimeoutError : AppError()
    object Unauthorized : AppError()

    // Firebase specific
    object UserNotFound : AppError()
    object DocumentNotFound : AppError()

    // Catch-all for unexpected
    data class Unknown(val throwable: Throwable) : AppError()
}
