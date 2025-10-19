package com.ajijul.elnaz.features_manager

import android.content.Context
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

class DynamicFeatureInstaller(context: Context) {

    private val splitInstallManager = SplitInstallManagerFactory.create(context)

    fun isModuleInstalled(moduleName: String): Boolean =
        splitInstallManager.installedModules.contains(moduleName)

    fun installModule(moduleName: String, onResult: (isSuccess: Boolean) -> Unit) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                ElnazLogger.d(TAG, "Module [$moduleName] installed successfully")
                onResult(true)
            }
            .addOnFailureListener {
                ElnazLogger.d(TAG, "Failed to install module [$moduleName]: ${it.message}")
                onResult(false)
            }
    }
}