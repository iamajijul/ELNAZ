package com.ajijul.elnaz.data.repository.auth

import com.ajijul.elnaz.data.network.FirebaseAuthSource
import com.ajijul.elnaz.data.network.FirebaseFirestoreDataSource
import com.ajijul.elnaz.data.network.firebase.FirestoreCollections
import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.UserRole
import com.ajijul.elnaz.domain.model.enums.AppError
import com.ajijul.elnaz.domain.model.enums.Resource
import javax.inject.Inject

class AuthRepositoryImplementation @Inject constructor(
    private val authDataSource: FirebaseAuthSource,
    private val firestoreDataSource: FirebaseFirestoreDataSource
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Resource<UserModel?> {
        return try {
            val user = authDataSource.login(email, password)
            if (user != null) {
                val currentUser = firestoreDataSource.getFirestoreDocument<UserModel>(
                    FirestoreCollections.USERS,
                    user.uid
                )
                Resource.Success(currentUser)
            } else {
                Resource.Error(AppError.UserNotFound)
            }
        } catch (e: Exception) {
            Resource.Error(AppError.Unknown(e))
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        role: UserRole
    ): Resource<UserModel?> {
        return try {
            val user = authDataSource.signup(email, password)
            if (user != null) {
                val userModel = UserModel(
                    uid = user.uid,
                    email = email,
                    name = name,
                    role = role,
                    createdAt = System.currentTimeMillis()
                )

                firestoreDataSource.setFirestoreDocument(
                    FirestoreCollections.USERS,
                    user.uid,
                    userModel
                )
                Resource.Success(userModel)
            } else {
                Resource.Error(AppError.Unauthorized)
            }
        } catch (e: Exception) {
            Resource.Error(AppError.Unknown(e))
        }
    }

    override suspend fun logout(): Resource<Boolean> {

        return try {
            authDataSource.logout()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(AppError.Unknown(e))
        }
    }

    override suspend fun currentUser(): Resource<UserModel?> {
        return try {
            val user = authDataSource.getCurrentUser()
            if (user != null) {
                val currentUser = firestoreDataSource.getFirestoreDocument<UserModel>(
                    FirestoreCollections.USERS,
                    user.uid
                )
                Resource.Success(currentUser)
            } else {
                Resource.Error(AppError.UserNotFound)
            }
        } catch (e: Exception) {
            Resource.Error(AppError.Unknown(e))
        }
    }
}