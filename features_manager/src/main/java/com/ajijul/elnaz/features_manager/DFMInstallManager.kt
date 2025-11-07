package com.ajijul.elnaz.features_manager

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DFMInstallManager @Inject constructor(
    @ApplicationContext context: Context
) : DynamicFeatureInstaller {

    private val manager = SplitInstallManagerFactory.create(context)

    override fun isModuleInstalled(moduleName: String): Boolean =
        manager.installedModules.contains(moduleName)

    override fun installModule(moduleName: String): Flow<SplitInstallSessionStatus> = callbackFlow {
        // Already installed? Emit INSTALLED and finish
        if (isModuleInstalled(moduleName)) {
            trySend(SplitInstallSessionStatus.INSTALLED)
            channel.close()
            return@callbackFlow
        }

        // Build request
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        var sessionId = 0

        // Start install
        manager.startInstall(request)
            .addOnSuccessListener { id -> sessionId = id }
            .addOnFailureListener {
                trySend(SplitInstallSessionStatus.FAILED)
                channel.close()
            }

        // Listen for updates
        val listener = SplitInstallStateUpdatedListener { state ->
            if (state.sessionId() != sessionId) return@SplitInstallStateUpdatedListener

            val status = when (state.status()) {
                0 -> SplitInstallSessionStatus.UNKNOWN
                1 -> SplitInstallSessionStatus.PENDING
                2 -> SplitInstallSessionStatus.DOWNLOADING
                3 -> SplitInstallSessionStatus.DOWNLOADED
                4 -> SplitInstallSessionStatus.INSTALLING
                5 -> SplitInstallSessionStatus.INSTALLED
                6 -> SplitInstallSessionStatus.FAILED
                7 -> SplitInstallSessionStatus.CANCELED
                8 -> SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION
                else -> SplitInstallSessionStatus.UNKNOWN
            }

            trySend(status)

            // Done?
            if (status == SplitInstallSessionStatus.INSTALLED ||
                status == SplitInstallSessionStatus.FAILED ||
                status == SplitInstallSessionStatus.CANCELED
            ) {
                channel.close()
            }
        }

        manager.registerListener(listener)

        // Cleanup on cancel
        awaitClose {
            manager.unregisterListener(listener)
        }
    }
}