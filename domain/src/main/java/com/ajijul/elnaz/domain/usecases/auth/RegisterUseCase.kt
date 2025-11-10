package com.ajijul.elnaz.domain.usecases.auth

import com.ajijul.elnaz.domain.repository.auth.AuthRepository
import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.domain.model.enums.UserRole
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.user.UserPreferenceRepository

class RegisterUseCase(
    private val repo: AuthRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        mobile: String,
        address: String,
        password: String,
        role: UserRole
    ): Resource<UserModel?> {
        return when (val result = repo.register(
            name = name,
            email = email,
            mobile = mobile,
            address = address,
            password = password,
            role = role
        )) {
            is Resource.Error -> result
            is Resource.Loading -> result
            is Resource.Success -> {
                result.data?.let { userPreferenceRepository.updateUser(it) }
                result
            }
        }
    }
}