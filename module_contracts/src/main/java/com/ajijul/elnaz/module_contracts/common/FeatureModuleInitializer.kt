package com.ajijul.elnaz.module_contracts.common

interface FeatureModuleInitializer {
    val featureModuleName: String
    fun initialize(contractor: FeatureModuleConnectorHolder)
}