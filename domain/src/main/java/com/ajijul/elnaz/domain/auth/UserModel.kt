package com.ajijul.elnaz.domain.auth

import com.ajijul.elnaz.domain.auth.UserRole

data class UserModel(
    val uid: String,
    val email: String,
    val name: String,
    val role: UserRole,
    val createdAt: Long
)