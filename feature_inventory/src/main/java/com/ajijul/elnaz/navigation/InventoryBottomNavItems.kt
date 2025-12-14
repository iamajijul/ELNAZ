package com.ajijul.elnaz.navigation

import androidx.annotation.DrawableRes
import com.ajijul.elnaz.resources.R

sealed class InventoryBottomNavItems(
    val route: String,
    val label: Int,
    @param:DrawableRes val icon: Int
) {
    object Products : InventoryBottomNavItems(
        AllInventoryScreenTypes.Products.identifier,
        R.string.nav_products_title,
        R.drawable.ic_products
    )

    object Orders :
        InventoryBottomNavItems(
            AllInventoryScreenTypes.Orders.identifier,
            R.string.nav_orders_title,
            R.drawable.ic_orders
        )

    object Warehouse :
        InventoryBottomNavItems(
            AllInventoryScreenTypes.Warehouses.identifier,
            R.string.nav_warehouse_title,
            R.drawable.ic_warehouse
        )

    object Category : InventoryBottomNavItems(
        AllInventoryScreenTypes.Categories.identifier,
        R.string.nav_category_title,
        R.drawable.ic_category
    )

    object Users :
        InventoryBottomNavItems(
            AllInventoryScreenTypes.Users.identifier,
            R.string.nav_users_title,
            R.drawable.ic_users
        )
}