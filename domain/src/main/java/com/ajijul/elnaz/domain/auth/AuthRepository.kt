package com.ajijul.elnaz.domain.auth

import com.ajijul.elnaz.domain.model.enums.Resource

interface AuthRepository {

    suspend fun login(email: String, password: String): Resource<UserModel?>

    suspend fun register(
        name: String,
        email: String,
        mobile : String,
        address : String,
        password: String,
        role: UserRole = UserRole.STAFF
    ): Resource<UserModel?>

    suspend fun logout(): Resource<Boolean>

    suspend fun currentUser(): Resource<UserModel?>

}