package com.ajijul.elnaz.domain.auth

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<UserModel?>
    suspend fun register(
        name: String,
        email: String,
        password: String,
        role: UserRole = UserRole.STAFF
    ): Result<UserModel?>

    suspend fun logout(): Result<Boolean>
    suspend fun currentUser(): Result<UserModel?>
}