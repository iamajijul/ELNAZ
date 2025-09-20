package com.ajijul.elnaz.auth.utils

import com.ajijul.elnaz.domain.auth.UserModel

sealed class AuthState {
    object Loading : AuthState()
    data class AuthenticatedUser(var userModel: UserModel?) : AuthState()
    object UnAuthenticatedUser : AuthState()
}
