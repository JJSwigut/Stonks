package com.jjswigut.stonks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.jjswigut.stonks.feature.stonklist.StonkListScreen
import com.jjswigut.stonks.ui.theme.StonksTheme
import org.koin.core.component.KoinComponent

/**
 * Main Activity class that sets up the layout for the application.
 *
 * Inherits from ComponentActivity and implements KoinComponent for dependency injection.
 */
class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            StonksTheme {
                StonkListScreen()
            }
        }
    }
}
