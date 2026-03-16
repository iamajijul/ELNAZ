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
){
    // ----------------------------------------------------------------
    // 1. BOTTOM NAVIGATION VISIBILITY (UI Structure)
    // ----------------------------------------------------------------

    val canSeeOrdersTab: Boolean
        get() = true

    val canSeeProductsTab: Boolean
        get() = true


    val canSeeCategoriesTab: Boolean
        get() = role == UserRole.SUPER_ADMIN || role == UserRole.ADMIN

    val canSeeWarehousesTab: Boolean
        get() = role == UserRole.SUPER_ADMIN || role == UserRole.ADMIN


    val canSeeUsersTab: Boolean
        get() = role == UserRole.SUPER_ADMIN

    // ----------------------------------------------------------------
    // 2. ON-SCREEN ACTION PERMISSIONS (Buttons inside the tabs)
    // ----------------------------------------------------------------

    // USERS SCREEN
    val canManageUsers: Boolean
        get() = role == UserRole.SUPER_ADMIN

    // WAREHOUSES SCREEN
    val canAddWarehouse: Boolean
        get() = role == UserRole.SUPER_ADMIN

    // CATEGORIES SCREEN
    val canManageCategory: Boolean
        get() = role == UserRole.SUPER_ADMIN || role == UserRole.ADMIN

    // PRODUCTS SCREEN
    val canEditProduct: Boolean
        get() = role == UserRole.SUPER_ADMIN || role == UserRole.ADMIN

    val canRestockOrSell: Boolean
        get() = true
}