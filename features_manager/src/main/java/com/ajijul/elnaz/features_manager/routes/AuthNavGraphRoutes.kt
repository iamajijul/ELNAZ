package com.ajijul.elnaz.features_manager.routes

sealed class AuthNavGraphRoutes(val identifier: String) {
    object Splash : AuthNavGraphRoutes("splash")
    object Login : AuthNavGraphRoutes("login")
    object Register : AuthNavGraphRoutes("register")
    object ForgotPassword : AuthNavGraphRoutes("forgot_password")
}