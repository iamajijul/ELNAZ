package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.UserRole

data class UserModel(
    val uid: String,
    val name: String,
    val email: String,
    val mobile: String,
    val address: String,
    val role: UserRole,
    val createdAt: Long
)