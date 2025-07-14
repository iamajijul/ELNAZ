package com.ajijul.elnaz.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.FirebaseApp

class FirebaseInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val appContext = context.applicationContext
        FirebaseApp.initializeApp(appContext)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}