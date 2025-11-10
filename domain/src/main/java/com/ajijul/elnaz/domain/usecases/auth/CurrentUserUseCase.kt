package com.ajijul.elnaz.domain.usecases.auth

import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.domain.model.enums.AppError
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.user.UserPreferenceRepository

class CurrentUserUseCase(
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(): Resource<UserModel?> {
        val user = userPreferenceRepository.getUser()
        return if (user != null && user.uid.isNotEmpty())
            Resource.Success(user)
        else Resource.Error(AppError.Unauthorized)
    }
}