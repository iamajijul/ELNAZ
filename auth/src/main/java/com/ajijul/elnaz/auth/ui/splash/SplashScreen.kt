package com.ajijul.elnaz.auth.ui.splash

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.navigation.AuthScreen
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.ItemOnCenteredColumn
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    nanHostController: NavHostController? = null
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val isAuthenticated = viewModel.splashUiState.collectAsState()

    LaunchedEffect(isAuthenticated.value) {
        when (isAuthenticated.value) {

            is SplashUiState.AuthenticatedUser -> {

            }

            SplashUiState.Loading -> {
            }

            SplashUiState.UnAuthenticatedUser -> {
                delay(2000)
                nanHostController?.navigate(AuthScreen.Login.identifier)
            }

        }

    }

    when (isAuthenticated.value) {

        SplashUiState.Loading -> {
            SplashContent()
        }

        is SplashUiState.AuthenticatedUser -> {
            SplashContent(stringResource(id = R.string.welcome))
        }

        SplashUiState.UnAuthenticatedUser -> {
            SplashContent()
        }
    }

}

@Composable
fun SplashContent(
    text: String? = null,
    showProgress: Boolean = true
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
