package com.jjswigut.stonks.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays a loader card if the isLoading parameter is true.
 *
 * @param modifier A Modifier that is applied to the loader card.
 * @param isLoading A Boolean that controls whether the loader is displayed or not.
 */
@Composable
fun Loader(
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {
    if (isLoading) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface),
            modifier = modifier.size(70.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = modifier.size(40.dp),
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}
