package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

class LoginUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(email: String, password: String): UserModel? {
        val userModel = repo.login(email, password)
        if (userModel != null) {
            userPreferenceRepository.updateUser(userModel)
        }
        return userModel
    }
}