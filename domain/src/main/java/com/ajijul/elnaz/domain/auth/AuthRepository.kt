package com.ajijul.elnaz.domain.auth

import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.UserRole

interface AuthRepository {
    suspend fun login(email: String, password: String): UserModel
    suspend fun register(
        name: String,
        email: String,
        password: String,
        role: UserRole = UserRole.STAFF
    ): UserModel

    suspend fun logout()
    suspend fun currentUser(): UserModel?
}