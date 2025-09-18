package com.ajijul.elnaz.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.ajijul.elnaz.components.AppNavigation
import com.ajijul.elnaz.core.ui.theme.ElnazInventoryTheme
import com.ajijul.elnaz.navigation.AppNavGraph
import com.ajijul.elnaz.startup.FeatureModuleInstaller
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //region VARIABLES

    @Inject
    lateinit var featureModuleInstaller: FeatureModuleInstaller

    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        setContent {
            ElnazInventoryTheme(content = { AppNavGraph(featureModuleInstaller) })
        }
    }
}