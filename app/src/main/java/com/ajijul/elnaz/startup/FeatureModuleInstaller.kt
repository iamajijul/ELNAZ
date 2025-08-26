package com.ajijul.elnaz.startup

import android.content.Context
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureModuleInstaller @Inject constructor(
    @ApplicationContext context: Context
) {

    private val splitInstallManager = SplitInstallManagerFactory.create(context)

    fun installModule(moduleName: String, onSuccess: () -> Unit) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                ElnazLogger.d(TAG, "Module [$moduleName] installed successfully")
                onSuccess()
            }
            .addOnFailureListener {
                ElnazLogger.d(TAG, "Failed to install module [$moduleName]: ${it.message}")
            }
    }
}