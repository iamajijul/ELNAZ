package com.ajijul.elnaz.auth.ui.splash

import com.ajijul.elnaz.domain.auth.UserModel

sealed class SplashUiState {
    object Loading : SplashUiState()
    data class AuthenticatedUser(val userModel: UserModel?) : SplashUiState()
    object UnAuthenticatedUser : SplashUiState()
}