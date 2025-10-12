package com.ajijul.elnaz.module_contracts.inventory

import com.ajijul.elnaz.module_contracts.common.FeatureContract

interface InventoryFeatureContracts : FeatureContract {
    fun registerGraph(
        builder: Any,
        navController: Any
    )
}