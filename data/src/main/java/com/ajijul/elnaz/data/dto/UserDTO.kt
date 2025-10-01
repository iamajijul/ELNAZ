package com.ajijul.elnaz.data.dto

import com.ajijul.elnaz.domain.auth.UserRole
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

data class UserDTO(
    @DocumentId
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val mobile: String = "",
    val address: String = "",
    val role: UserRole = UserRole.STAFF,
    @ServerTimestamp
    val createdAt: Timestamp? = null
)