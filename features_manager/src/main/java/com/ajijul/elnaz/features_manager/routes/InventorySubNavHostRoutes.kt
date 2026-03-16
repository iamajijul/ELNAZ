package com.ajijul.elnaz.features_manager.routes

sealed class InventorySubNavHostRoutes(
    val identifier: String,
    val moduleName: String,
    val deepLinks: List<String>
) {
    object PRODUCTS : InventorySubNavHostRoutes("product_graph", "feature_products", emptyList())
    object ORDERS : InventorySubNavHostRoutes("order_graph", "feature_orders", emptyList())
    object CATEGORY : InventorySubNavHostRoutes("category_graph", "feature_category", emptyList())
    object WAREHOUSE : InventorySubNavHostRoutes("warehouse_graph", "feature_warehouse", emptyList())
    object USERS : InventorySubNavHostRoutes("user_graph", "feature_user", emptyList())
}