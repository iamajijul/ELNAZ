package com.ajijul.elnaz.startup

import android.content.Context
import androidx.startup.Initializer
import com.ajijul.elnaz.extension.TAG
import com.ajijul.elnaz.logger.ElnazLogger
import com.google.firebase.FirebaseApp

class FirebaseInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val appContext = context.applicationContext
        val firebaseApp = FirebaseApp.initializeApp(appContext)
        if (firebaseApp == null) {
            ElnazLogger.e(TAG, "Firebase initialization failed.")
        } else {
            ElnazLogger.i(TAG, "Firebase initialized successfully. ${firebaseApp.name}")
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}