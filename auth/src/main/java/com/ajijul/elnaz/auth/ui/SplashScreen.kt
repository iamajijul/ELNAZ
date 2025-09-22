package com.ajijul.elnaz.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.auth.utils.AuthState
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.ItemsOnCenteredColumn

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
            SplashContent(showProgress = true)
        }

        is AuthState.AuthenticatedUser -> {
            SplashContent(stringResource(id = R.string.welcome))
        }

        AuthState.UnAuthenticatedUser -> {
            SplashContent(stringResource(id = R.string.please_login))
        }
    }

}

@Composable
fun SplashContent(
    text: String? = null,
    showProgress: Boolean = false
) {
    ItemsOnCenteredColumn {
//        Image(
//            painter = painterResource(androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            contentDescription = null
//        )

        if (showProgress) {
            AppProgress()
        }

        text?.let {
            AppText(
                text = it,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
