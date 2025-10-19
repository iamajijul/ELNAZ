package com.ajijul.elnaz.di.utilities

import android.content.Context
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilitiesModule {
    @Provides
    @Singleton
    fun provideDynamicFeatureInstaller(@ApplicationContext context: Context) =
        DynamicFeatureInstaller(context)
}