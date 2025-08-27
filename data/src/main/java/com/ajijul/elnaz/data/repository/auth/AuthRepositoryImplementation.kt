package com.ajijul.elnaz.data.repository.auth

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.UserRole

class AuthRepositoryImplementation : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): UserModel? {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        role: UserRole
    ): UserModel? {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun currentUser(): UserModel? {
        TODO("Not yet implemented")
    }
}