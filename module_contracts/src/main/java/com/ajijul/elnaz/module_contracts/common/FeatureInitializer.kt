package com.ajijul.elnaz.module_contracts.common

interface FeatureInitializer {
    val featureModuleName: String
    fun initialize(installer: FeatureModuleContractor)
}