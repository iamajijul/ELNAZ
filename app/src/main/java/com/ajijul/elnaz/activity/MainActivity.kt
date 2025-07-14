package com.ajijul.elnaz.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ajijul.elnaz.components.AppNavigation
import com.ajijul.elnaz.core.ui.theme.ElnazInventoryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElnazInventoryTheme(content = { AppNavigation() })
        }
    }
}