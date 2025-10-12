package com.ajijul.elnaz.auth.navigation

sealed class AuthScreen(val identifier: String) {
    object Splash : AuthScreen("splash")
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
    object ForgotPassword : AuthScreen("forgot_password")
}
