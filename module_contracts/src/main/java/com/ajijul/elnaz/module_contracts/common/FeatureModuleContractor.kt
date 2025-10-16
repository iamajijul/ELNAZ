package com.ajijul.elnaz.module_contracts.common

interface FeatureModuleContractor {
    fun <T : FeatureContract> getFeatureModule(apiClass: Class<T>): T?
    fun registerFeatureModule(featureKey: String, api: FeatureContract)
}