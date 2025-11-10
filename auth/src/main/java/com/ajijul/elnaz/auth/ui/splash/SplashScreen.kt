package com.ajijul.elnaz.auth.ui.splash

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.navigation.AuthScreen
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.ItemOnCenteredColumn
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun SplashScreen(
    nanHostController: NavHostController? = null,
    dynamicFeatureInstaller: DynamicFeatureInstaller
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val isAuthenticatedState = viewModel.splashUiState.collectAsState()

    LaunchedEffect(isAuthenticatedState.value) {
        when (isAuthenticatedState.value) {
            SplashUiState.UnAuthenticatedUser -> {
                delay(2000)
                nanHostController?.navigate(AuthScreen.Login.identifier){
                    popUpTo(AuthScreen.Splash.identifier) {
                        inclusive = true
                    }
                }
            }

            is SplashUiState.AuthenticatedUser -> {
                ElnazLogger.i(TAG, "DFM SplashUiState.AuthenticatedUser")
                nanHostController?.navigate(MainNavGraphRoutes.INVENTORY.identifier){
                    popUpTo(AuthScreen.Splash.identifier) {
                        inclusive = true
                    }
                }
            }

            SplashUiState.Loading -> {

            }
        }

    }

    when (isAuthenticatedState.value) {

        SplashUiState.Loading -> {
            SplashContent()
        }

        is SplashUiState.AuthenticatedUser -> {
            SplashContent(stringResource(id = R.string.welcome), showProgress = false)
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
