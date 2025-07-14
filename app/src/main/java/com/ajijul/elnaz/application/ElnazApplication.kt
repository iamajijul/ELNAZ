package com.ajijul.elnaz.application

import android.app.Application
import androidx.startup.AppInitializer
import com.ajijul.elnaz.di.annotations.IODispatcher
import com.ajijul.elnaz.startup.FirebaseInitializer
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltAndroidApp
class ElnazApplication : Application(), CoroutineScope {

    //region VARIABLES

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    @Inject
    @IODispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    //endregion

    //region LIFECYCLE

    override fun onCreate() {
        super.onCreate()
        appInitializer()
    }

    //endregion

    //region UTILS

    fun appInitializer() {
        launch(ioDispatcher) {
            AppInitializer.getInstance(this@ElnazApplication).apply {
                initializeComponent(FirebaseInitializer::class.java)
            }
        }
    }

    //endregion
}