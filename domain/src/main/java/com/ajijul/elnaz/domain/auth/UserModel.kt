package com.ajijul.elnaz.domain.auth

data class UserModel(
    val uid: String,
    val name: String,
    val email: String,
    val mobile: String,
    val address: String,
    val role: UserRole,
    val createdAt: Long
)