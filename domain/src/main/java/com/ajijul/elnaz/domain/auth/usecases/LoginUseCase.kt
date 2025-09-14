package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

class LoginUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<UserModel?> {
        return when (val result = repo.login(email, password)) {
            is Resource.Success -> {
                result.data?.let { user ->
                    userPreferenceRepository.updateUser(user)
                }
                result
            }
            is Resource.Error -> result // pass through error
            is Resource.Loading -> Resource.Loading // forward loading state
        }
    }
}