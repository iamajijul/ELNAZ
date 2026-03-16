package com.ajijul.elnaz.inventory_presentation.utils

import androidx.annotation.DrawableRes
import com.ajijul.elnaz.features_manager.routes.InventorySubNavHostRoutes
import com.ajijul.elnaz.features_manager.routes.MainNavGraphRoutes
import com.ajijul.elnaz.resources.R

sealed class InventoryBottomNavItems(
    val route: String,
    val label: Int,
    @param:DrawableRes val icon: Int
) {
    object Products : InventoryBottomNavItems(
        InventorySubNavHostRoutes.PRODUCTS.identifier,
        R.string.nav_products_title,
        R.drawable.ic_products
    )

    object Orders :
        InventoryBottomNavItems(
            InventorySubNavHostRoutes.ORDERS.identifier,
            R.string.nav_orders_title,
            R.drawable.ic_orders
        )

    object Category : InventoryBottomNavItems(
        InventorySubNavHostRoutes.CATEGORY.identifier,
        R.string.nav_category_title,
        R.drawable.ic_category
    )

    object Warehouse :
        InventoryBottomNavItems(
            InventorySubNavHostRoutes.WAREHOUSE.identifier,
            R.string.nav_warehouse_title,
            R.drawable.ic_warehouse
        )

    object Users :
        InventoryBottomNavItems(
            InventorySubNavHostRoutes.USERS.identifier,
            R.string.nav_users_title,
            R.drawable.ic_users
        )
}