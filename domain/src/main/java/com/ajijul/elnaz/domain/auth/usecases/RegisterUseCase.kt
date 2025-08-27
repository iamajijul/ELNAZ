package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.UserRole
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

class RegisterUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        role: UserRole
    ): UserModel? {
        val userModel = repo.register(name, email, password, role)
        if (userModel != null) {
            userPreferenceRepository.updateUser(userModel)
        }
        return userModel
    }
}