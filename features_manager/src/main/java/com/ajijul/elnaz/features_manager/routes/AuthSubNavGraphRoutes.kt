package com.ajijul.elnaz.features_manager.routes

sealed class AuthSubNavGraphRoutes(val identifier: String) {
    object Splash : AuthSubNavGraphRoutes("splash")
    object Login : AuthSubNavGraphRoutes("login")
    object Register : AuthSubNavGraphRoutes("register")
    object ForgotPassword : AuthSubNavGraphRoutes("forgot_password")
}