package com.ajijul.elnaz.module_contracts.inventory

import com.ajijul.elnaz.module_contracts.common.FeatureConnector

interface InventoryFeatureContracts : FeatureConnector {
    fun registerGraph(
        builder: Any,
        navController: Any
    )
}