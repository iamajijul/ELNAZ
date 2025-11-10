package com.ajijul.elnaz.domain.repository.user

import com.ajijul.elnaz.domain.model.UserModel

interface UserPreferenceRepository {
    suspend fun updateUser(userModel: UserModel)
    suspend fun getUser(): UserModel?
    suspend fun clearUser()
}