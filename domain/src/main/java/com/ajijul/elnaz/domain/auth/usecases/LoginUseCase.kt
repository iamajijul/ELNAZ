package com.ajijul.elnaz.domain.auth.usecases

import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.user.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<UserModel?>> = flow {
        emit(Resource.Loading)
        val result = repo.login(email, password)
        if (result is Resource.Success) {
            result.data?.let { user ->
                userPreferenceRepository.updateUser(user)
            }
        }
        emit(result)
    }
}