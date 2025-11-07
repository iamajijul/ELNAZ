package com.ajijul.elnaz.features_manager

import kotlinx.coroutines.flow.Flow

interface DynamicFeatureInstaller {
    fun isModuleInstalled(moduleName: String): Boolean
    fun installModule(moduleName: String): Flow<SplitInstallSessionStatus>
}