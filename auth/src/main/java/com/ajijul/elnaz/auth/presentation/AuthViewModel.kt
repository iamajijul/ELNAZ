package com.ajijul.elnaz.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.auth.ui.login.LoginUiState
import com.ajijul.elnaz.auth.ui.splash.SplashUiState
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
        validateLoginInput()
    }

    fun onPasswordValueChange(password: String) {
        _loginUiState.value = _loginUiState.value.copy(password = password)
        validateLoginInput()
    }

    private fun validateLoginInput() {
        val email = _loginUiState.value.email
        val password = _loginUiState.value.password
        val isLoading = _loginUiState.value.isLoading
        _loginUiState.value =
            _loginUiState.value.copy(isLoggedInButtonEnabled = email.isNotEmpty() && password.isNotEmpty() && !isLoading)
    }

    fun login() {
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
}