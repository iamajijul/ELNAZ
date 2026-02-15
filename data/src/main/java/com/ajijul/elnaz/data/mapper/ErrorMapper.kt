package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.domain.model.enums.AppError
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestoreException
import java.io.IOException
import java.util.concurrent.TimeoutException

fun Throwable.toAppError(): AppError {
    return when (this) {
        // --- Network Errors ---
        is IOException, is FirebaseNetworkException -> AppError.Network.ServerError
        is TimeoutException -> AppError.Network.Timeout

        // --- Authentication Errors (Login/Register) ---
        is FirebaseAuthInvalidCredentialsException -> AppError.Auth.InvalidCredentials // Wrong Password / Malformed Email
        is FirebaseAuthInvalidUserException -> AppError.Auth.UserNotFound // Email doesn't exist
        is FirebaseAuthUserCollisionException -> AppError.Auth.UserAlreadyExists // Registering with used email

        // --- Firestore / Database Errors ---
        is FirebaseFirestoreException -> {
            when (this.code) {
                FirebaseFirestoreException.Code.UNAVAILABLE -> AppError.Network.ServerError
                FirebaseFirestoreException.Code.PERMISSION_DENIED -> AppError.Auth.Unauthorized
                FirebaseFirestoreException.Code.NOT_FOUND -> AppError.Storage.ItemNotFound
                else -> AppError.Unknown(this)
            }
        }

        // --- Standard Kotlin Errors ---
        is IllegalArgumentException -> AppError.Validation.Custom(this.message ?: "Invalid Input") // e.g., empty fields

        // --- Catch All ---
        else -> AppError.Unknown(this)
    }
}