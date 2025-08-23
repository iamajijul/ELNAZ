package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository

class CurrentUserUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke() = repo.currentUser()
}