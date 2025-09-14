package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

class CurrentUserUseCase(
    private val authRepo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(): Resource<UserModel?> {
        val user = userPreferenceRepository.getUser()
        if (user != null) {
            return Resource.Success(user)
        }
        return authRepo.currentUser()
    }
}