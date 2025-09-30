package com.ajijul.elnaz.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.ui.login.LoginUiState
import com.ajijul.elnaz.auth.ui.splash.SplashUiState
import com.ajijul.elnaz.core.utils.isValidEmail
import com.ajijul.elnaz.di.annotations.IODispatcher
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.usecases.CurrentUserUseCase
import com.ajijul.elnaz.domain.auth.usecases.LoginUseCase
import com.ajijul.elnaz.domain.auth.usecases.LogoutUseCase
import com.ajijul.elnaz.domain.auth.usecases.RegisterUseCase
import com.ajijul.elnaz.domain.model.enums.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val currentUserUseCase: CurrentUserUseCase,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _splashUiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val splashUiState: StateFlow<SplashUiState> = _splashUiState

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    init {
        viewModelScope.launch(ioDispatcher) {
            val currentUserUseCase: Resource<UserModel?> = currentUserUseCase()
            when (currentUserUseCase) {
                is Resource.Error -> _splashUiState.value = SplashUiState.UnAuthenticatedUser
                Resource.Loading -> _splashUiState.value = SplashUiState.Loading
                is Resource.Success<UserModel?> -> _splashUiState.value =
                    SplashUiState.AuthenticatedUser(currentUserUseCase.data)
            }
        }
    }

    fun onEmailValueChange(email: String) {
        _loginUiState.value = _loginUiState.value.copy(email = email)
    }

    fun onPasswordValueChange(password: String) {
        _loginUiState.value = _loginUiState.value.copy(password = password)
    }

    fun login() {
        if (validateLoginInput().not()) return
        viewModelScope.launch(ioDispatcher) {
            val result = loginUseCase(_loginUiState.value.email, _loginUiState.value.password)
            when (result) {
                is Resource.Error -> {
                    _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                }

                Resource.Loading -> {
                    _loginUiState.value = _loginUiState.value.copy(isLoading = true)
                }

                is Resource.Success<*> -> {
                    _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun validateLoginInput(): Boolean {
        val email = _loginUiState.value.email
        var emailError: Int? = null
        var passwordError: Int? = null

        if (email.isEmpty() || !email.isValidEmail()) {
            emailError = R.string.login_screen_invalid_email
        }

        val password = _loginUiState.value.password
        if (password.isEmpty() || password.length < 6) {
            passwordError = R.string.login_screen_invalid_password
        }

        _loginUiState.value = _loginUiState.value.copy(
            emailError = emailError,
            passwordError = passwordError
        )

        return emailError == null && passwordError == null
    }
}