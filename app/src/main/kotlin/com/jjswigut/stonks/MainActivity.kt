package com.jjswigut.stonks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
                StonksTheme {
                    StonkListScreen()
                }
            }
        }
    }
}
