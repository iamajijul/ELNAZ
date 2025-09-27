package com.ajijul.elnaz.auth.ui.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: Boolean = false,
    val isLoggedInButtonEnabled: Boolean = false,
)