package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.model.enums.AppError
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.user.UserPreferenceRepository

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