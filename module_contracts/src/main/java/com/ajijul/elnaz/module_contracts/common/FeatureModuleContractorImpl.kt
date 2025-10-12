package com.ajijul.elnaz.module_contracts.common

@Suppress("UNCHECKED_CAST")
class FeatureModuleContractorImpl : FeatureModuleContractor {
    private val registry = mutableMapOf<String, FeatureContract>()

    override fun <T : FeatureContract> getApi(apiClass: Class<T>): T? {
        return registry.values.firstOrNull { apiClass.isInstance(it) } as? T
    }

    override fun registerFeature(featureKey: String, api: FeatureContract) {
        registry[featureKey] = api
    }
}