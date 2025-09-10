package com.ajijul.elnaz.domain.auth

data class UserModel(
    val uid: String,
    val email: String,
    val name: String,
    val role: UserRole,
    val createdAt: Long
)