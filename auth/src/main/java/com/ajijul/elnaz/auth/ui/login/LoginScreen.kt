package com.ajijul.elnaz.auth.ui.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.auth.R
import com.ajijul.elnaz.auth.navigation.AuthScreen
import com.ajijul.elnaz.auth.presentation.AuthViewModel
import com.ajijul.elnaz.core.ui.components.AppIconButton
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.AppTextFieldWithError
import com.ajijul.elnaz.core.ui.components.AppTextOnFilledButton
import com.ajijul.elnaz.core.ui.components.ItemOnCenteredColumn
import com.ajijul.elnaz.core.ui.components.PrimaryFilledButton
import com.ajijul.elnaz.core.ui.components.PrimaryTextButton
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSmallSize
import com.ajijul.elnaz.core.utils.AppDimens.appProgressSmallStroke
import com.ajijul.elnaz.core.utils.showToast
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes

@Composable
fun LoginScreen(
    navHostController: NavHostController? = null,
    dynamicFeatureInstaller: DynamicFeatureInstaller? = null,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val loginUiState = viewModel.loginUiState.collectAsState()
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            errorMessage = loginUiState.value.passwordError?.let { stringResource(it) },
            trailingIcon = {
                val image =
                    if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff
                AppIconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    iconImage = image
                )
            }
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
                AppProgress(size = appProgressSmallSize, strokeWidth = appProgressSmallStroke)
            } else {
                AppTextOnFilledButton(text = stringResource(R.string.login_screen_login))
            }
        }

    }

    LaunchedEffect(loginUiState.value.isLoggedIn) {
        if (loginUiState.value.isLoggedIn) {
            showToast(
                context = context,
                message = context.getString(
                    R.string.login_screen_toast_welcome,
                    loginUiState.value.loggedInUserName
                )
            )
            viewModel.clearLoginUiState()
            navHostController?.navigate(MainNavGraphRoutes.INVENTORY.identifier) {
                popUpTo(AuthScreen.Splash.identifier) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(loginUiState.value.loggedInErrorMsg) {
        loginUiState.value.loggedInErrorMsg?.let { errorResId ->
            showToast(
                context = context,
                message = context.getString(errorResId)
            )
            viewModel.clearErrorMessage()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowLoginScreen() {
    LoginScreen()
}