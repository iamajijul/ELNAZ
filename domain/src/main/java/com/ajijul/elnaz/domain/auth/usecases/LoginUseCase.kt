package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel

class LoginUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): UserModel =
        repo.login(email, password)
}