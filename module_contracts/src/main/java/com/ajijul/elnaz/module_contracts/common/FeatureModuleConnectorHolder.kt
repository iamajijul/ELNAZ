package com.ajijul.elnaz.module_contracts.common

interface FeatureModuleConnectorHolder {
    fun <T : FeatureConnector> getFeatureModuleConnector(apiClass: Class<T>): T?
    fun registerFeatureModuleConnector(featureKey: String, api: FeatureConnector)
}