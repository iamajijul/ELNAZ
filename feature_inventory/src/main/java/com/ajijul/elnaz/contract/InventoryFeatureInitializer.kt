package com.ajijul.elnaz.contract

import com.ajijul.elnaz.feature_inventory.BuildConfig
import com.ajijul.elnaz.module_contracts.common.FeatureInitializer
import com.ajijul.elnaz.module_contracts.common.FeatureModuleContractor

class InventoryFeatureInitializer : FeatureInitializer {
    override val featureModuleName: String
        get() = BuildConfig.MODULE_NAME

    override fun initialize(installer: FeatureModuleContractor) {
        TODO("Not yet implemented")
    }
}