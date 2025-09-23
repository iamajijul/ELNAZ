package com.ajijul.elnaz.auth.ui.splash

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.ItemOnCenteredColumn

@Composable
fun SplashScreen(
    nanHostController: NavHostController
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val isAuthenticated = viewModel.splashUiState.collectAsState()

    LaunchedEffect(isAuthenticated) {
        when (isAuthenticated.value) {

            is SplashUiState.AuthenticatedUser -> {

            }

            SplashUiState.Loading -> {

            }

            SplashUiState.UnAuthenticatedUser -> {

            }

        }

    }

    when (isAuthenticated.value) {

        SplashUiState.Loading -> {
            SplashContent(showProgress = true)
        }

        is SplashUiState.AuthenticatedUser -> {
            SplashContent(stringResource(id = R.string.welcome))
        }

        SplashUiState.UnAuthenticatedUser -> {
            SplashContent(stringResource(id = R.string.please_login))
        }
    }

}

@Composable
fun SplashContent(
    text: String? = null,
    showProgress: Boolean = false
) {
    ItemOnCenteredColumn {
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
