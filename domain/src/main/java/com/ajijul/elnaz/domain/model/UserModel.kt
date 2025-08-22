package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.UserRole

data class User(
    val uid: String,
    val name: String,
    val role: UserRole
)