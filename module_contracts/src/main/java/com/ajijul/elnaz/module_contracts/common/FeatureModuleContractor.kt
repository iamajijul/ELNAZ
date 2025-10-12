package com.ajijul.elnaz.module_contracts.common

interface FeatureModuleContractor {
    fun <T : FeatureContract> getApi(apiClass: Class<T>): T?
    fun registerFeature(featureKey: String, api: FeatureContract)
}