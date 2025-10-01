package com.ajijul.elnaz.auth.ui.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.navigation.AuthScreen
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.ui.components.AppProgressOnButton
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.AppTextFieldWithError
import com.ajijul.elnaz.core.ui.components.AppTextOnFilledButton
import com.ajijul.elnaz.core.ui.components.ItemOnCenteredColumn
import com.ajijul.elnaz.core.ui.components.PrimaryFilledButton
import com.ajijul.elnaz.core.ui.components.PrimaryTextButton
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSmallSize

@Composable
fun LoginScreen(
    navHostController: NavHostController? = null,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val loginUiState = viewModel.loginUiState.collectAsState()

    ItemOnCenteredColumn {


        AppTextFieldWithError(
            value = loginUiState.value.email,
            onValueChange = { email ->
                viewModel.onEmailValueChange(email)
            },
            label = stringResource(R.string.login_screen_email),
            isError = loginUiState.value.emailError != null,
            errorMessage = loginUiState.value.emailError?.let { stringResource(it) }
        )

        AppTextFieldWithError(
            value = loginUiState.value.password,
            onValueChange = { password ->
                viewModel.onPasswordValueChange(password)
            },
            label = stringResource(R.string.login_screen_password),
            isError = loginUiState.value.passwordError != null,
            errorMessage = loginUiState.value.passwordError?.let { stringResource(it) }
        )

        PrimaryTextButton(onClick = {
            viewModel.login()
        }, modifier = Modifier.align(Alignment.Start)) {
            AppText(text = stringResource(R.string.login_screen_forget_password))
        }

        PrimaryFilledButton(
            onClick = {
                viewModel.login()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loginUiState.value.isLoading
        ) {
            if (loginUiState.value.isLoading) {
                AppProgressOnButton()
            } else {
                AppTextOnFilledButton(text = stringResource(R.string.login_screen_login))
            }
        }


        PrimaryFilledButton(
            onClick = {
                // viewModel.login()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            AppTextOnFilledButton(text = stringResource(R.string.login_screen_register))
        }

    }

    LaunchedEffect(loginUiState.value.isLoggedIn) {
        if (loginUiState.value.isLoggedIn) {
            // navHostController?.navigate(AuthScreen.Register.route)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowLoginScreen() {
    LoginScreen()
}