package com.ajijul.elnaz.data.repository.user

import androidx.datastore.core.DataStore
import com.ajijul.elnaz.data.datastore.proto.UserPreferences
import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.domain.model.enums.UserRole
import com.ajijul.elnaz.domain.repository.user.UserPreferenceRepository
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
                .setName(userModel.name)
                .setEmail(userModel.email)
                .setMobile(userModel.mobile)
                .setAddress(userModel.address)
                .setRole(userModel.role.name)
                .setCreatedAt(userModel.createdAt)
                .build()
        }
    }

    override suspend fun getUser(): UserModel {
        val role =
            dataStore.data.map { it.role }.first()?.takeIf { it.isNotEmpty() }?.let {
                UserRole.valueOf(it)
            } ?: UserRole.STAFF

        return UserModel(
            uid = dataStore.data.map { it.uid }.first(),
            name = dataStore.data.map { it.name }.first(),
            email = dataStore.data.map { it.email }.first(),
            mobile = dataStore.data.map { it.mobile }.first(),
            address = dataStore.data.map { it.address }.first(),
            role = role,
            createdAt = dataStore.data.map { it.createdAt }.first()
        )
    }

    override suspend fun clearUser() {
        dataStore.updateData { prefs ->
            prefs.toBuilder().clear().build()
        }
    }
}