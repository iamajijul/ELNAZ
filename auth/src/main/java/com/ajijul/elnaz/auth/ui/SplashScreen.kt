package com.ajijul.elnaz.auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.auth.utils.AuthState
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.ui.components.AppText

@Composable
fun SplashScreen(
    nanHostController: NavHostController
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val isAuthenticated = viewModel.authState.collectAsState()

    LaunchedEffect(isAuthenticated) {
        when (isAuthenticated.value) {

            is AuthState.AuthenticatedUser -> {
            }

            AuthState.Loading -> {

            }

            AuthState.UnAuthenticatedUser -> {

            }

        }

    }

    when (isAuthenticated.value) {

        AuthState.Loading -> {
            AppProgress()
        }

        is AuthState.AuthenticatedUser -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),             // occupy the whole screen
                contentAlignment = Alignment.Center // center all children vertically & horizontally
            )
            {
                AppText(
                    text = stringResource(id = R.string.welcome),
                    style = MaterialTheme.typography.titleLarge
                )
            }

        }

        AuthState.UnAuthenticatedUser -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),             // occupy the whole screen
                contentAlignment = Alignment.Center // center all children vertically & horizontally
            )
            {
                AppText(
                    text = stringResource(id = R.string.please_login),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }

}