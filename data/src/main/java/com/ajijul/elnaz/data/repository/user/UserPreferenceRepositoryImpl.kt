package com.ajijul.elnaz.data.repository.user

import androidx.datastore.core.DataStore
import com.ajijul.elnaz.data.datastore.proto.UserPreferences
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.UserRole
import com.ajijul.elnaz.domain.user.UserPreferenceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<UserPreferences>
) : UserPreferenceRepository {

    override suspend fun updateUser(userModel: UserModel) {
        dataStore.updateData { prefs ->
            prefs.toBuilder()
                .setUid(userModel.uid)
                .setEmail(userModel.email)
                .setName(userModel.name)
                .setRole(userModel.role.name)
                .setCreatedAt(userModel.createdAt)
                .build()
        }
    }

    override suspend fun getUser(): UserModel {
        return UserModel(
            uid = dataStore.data.map { it.uid }.first(),
            email = dataStore.data.map { it.email }.first(),
            name = dataStore.data.map { it.name }.first(),
            role = UserRole.valueOf(dataStore.data.map { it.role }.first()),
            createdAt = dataStore.data.map { it.createdAt }.first()
        )
    }

    override suspend fun clearUser() {
        dataStore.updateData { prefs ->
            prefs.toBuilder().clear().build()
        }
    }
}