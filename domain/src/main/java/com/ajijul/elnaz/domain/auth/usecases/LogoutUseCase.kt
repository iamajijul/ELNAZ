package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

class LogoutUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(): Resource<Boolean> {
        return when (val result = repo.logout()) {
            is Resource.Error -> result
            Resource.Loading -> result
            is Resource.Success<Boolean> -> {
                userPreferenceRepository.clearUser()
                result
            }
        }
    }
}