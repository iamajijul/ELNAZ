package com.ajijul.elnaz.auth.presentation

import com.ajijul.elnaz.domain.auth.usecases.CurrentUserUseCase
import com.ajijul.elnaz.domain.auth.usecases.LoginUseCase
import com.ajijul.elnaz.domain.auth.usecases.LogoutUseCase
import com.ajijul.elnaz.domain.auth.usecases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    registerUseCase: RegisterUseCase,
    loginUseCase: LoginUseCase,
    currentUserUseCase: CurrentUserUseCase,
    logoutUseCase: LogoutUseCase
) {

}