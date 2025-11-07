package com.ajijul.elnaz.di.utilities

import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import com.ajijul.elnaz.features_manager.DFMInstallManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilitiesBinder {
    @Binds
    @Singleton
    abstract fun bindDynamicFeatureInstallerWithImplementation(
        splitManager: DFMInstallManager
    ): DynamicFeatureInstaller
}