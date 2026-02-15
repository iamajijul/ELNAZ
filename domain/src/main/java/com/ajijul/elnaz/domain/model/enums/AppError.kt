package com.ajijul.elnaz.domain.model.enums

sealed interface AppError {
    sealed interface Auth : AppError {
        object InvalidEmail : Auth
        object InvalidPassword : Auth
        object InvalidCredentials : Auth
        object UserNotFound : Auth
        object UserAlreadyExists : Auth
        object Unauthorized : Auth
    }

    sealed interface Network : AppError {
        object NoInternet : Network
        object Timeout : Network
        object ServerError : Network
        object Serialization : Network
    }

    sealed interface Storage : AppError {
        object ItemNotFound : Storage
        object ItemAlreadyExists : Storage
        object OperationFailed : Storage
        object DiskFull : Storage
    }

    sealed interface Validation : AppError {

        object FieldEmpty : Validation

        data class MinLength(val requiredLength: Int) : Validation

        data class PatternMismatch(val description: String) : Validation

        object InvalidFormat : Validation

        data class Custom(val message: String) : Validation
    }

    data class Unknown(val throwable: Throwable? = null) : AppError
}
