package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.UserRole

class RegisterUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        role: UserRole
    ): UserModel =
        repo.register(name, email, password, role)
}