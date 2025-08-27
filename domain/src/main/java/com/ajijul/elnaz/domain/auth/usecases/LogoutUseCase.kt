package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

class LogoutUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(): Boolean {
        val isSuccess = repo.logout()
        if (isSuccess)
            userPreferenceRepository.clearUser()
        return isSuccess
    }
}