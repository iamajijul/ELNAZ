package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

class CurrentUserUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(): UserModel? {
        val user = userPreferenceRepository.getUser()
        if (user != null) {
            return user
        }
        return repo.currentUser()
    }
}