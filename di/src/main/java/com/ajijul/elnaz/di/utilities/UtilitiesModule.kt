package com.ajijul.elnaz.di.utilities

import android.content.Context
import com.ajijul.elnaz.core.contract.DynamicFeatureModuleInstaller
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilitiesModule {
    @Provides
    fun provideDynamicFeatureModuleInstaller(@ApplicationContext context: Context) =
        DynamicFeatureModuleInstaller(context)

}