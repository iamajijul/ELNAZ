package com.ajijul.elnaz.module_contracts.common

@Suppress("UNCHECKED_CAST")
class FeatureModuleConnectorHolderImpl : FeatureModuleConnectorHolder {
    private val registry = mutableMapOf<String, FeatureConnector>()

    override fun <T : FeatureConnector> getFeatureModuleConnector(apiClass: Class<T>): T? {
        return registry.values.firstOrNull { apiClass.isInstance(it) } as? T
    }

    override fun registerFeatureModuleConnector(featureKey: String, api: FeatureConnector) {
        registry[featureKey] = api
    }
}