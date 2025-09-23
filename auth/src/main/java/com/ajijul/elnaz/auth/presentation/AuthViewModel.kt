package com.ajijul.elnaz.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}