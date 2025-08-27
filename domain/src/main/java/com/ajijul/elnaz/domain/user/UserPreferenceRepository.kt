package com.ajijul.elnaz.domain.user

import com.ajijul.elnaz.domain.auth.UserModel

interface UserPreferenceRepository {
    suspend fun updateUser(userModel: UserModel)
    suspend fun getUser(): UserModel?
    suspend fun clearUser()
}