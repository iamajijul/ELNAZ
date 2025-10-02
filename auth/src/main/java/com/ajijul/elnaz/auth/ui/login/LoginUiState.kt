package com.ajijul.elnaz.auth.ui.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val loggedInUserName: String = "",
    val loggedInErrorMsg: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
)