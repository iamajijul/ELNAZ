package com.ajijul.elnaz.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.ajijul.elnaz.core.ui.theme.ElnazInventoryTheme
import com.ajijul.elnaz.navigation.MainNavGraph
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //region VARIABLES

    @Inject
    lateinit var dynamicFeatureInstaller: DynamicFeatureInstaller

    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        setContent {
            ElnazInventoryTheme(content = { MainNavGraph(dynamicFeatureInstaller) })
        }
    }
}