package com.ajijul.elnaz.auth.utils

import com.ajijul.elnaz.domain.model.UserModel

sealed class SplashUiState {
    object Loading : SplashUiState()
    data class AuthenticatedUser(val userModel: UserModel?) : SplashUiState()
    object UnAuthenticatedUser : SplashUiState()
}