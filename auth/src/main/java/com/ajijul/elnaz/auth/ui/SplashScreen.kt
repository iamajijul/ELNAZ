package com.ajijul.elnaz.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.navigation.AuthScreen
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.auth.utils.AuthState
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.utils.NavGraphRoutes

@Composable
fun SplashScreen(
    nanHostController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val isAuthenticated = viewModel.authState.collectAsState()

    when (isAuthenticated.value) {

        is AuthState.AuthenticatedUser -> nanHostController.navigate(
            NavGraphRoutes.MAIN.route
        )

        AuthState.Loading -> AppProgress()

        AuthState.UnAuthenticatedUser -> nanHostController.navigate(
            AuthScreen.Login.route
        )

    }
}