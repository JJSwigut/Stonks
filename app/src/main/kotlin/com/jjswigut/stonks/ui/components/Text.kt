package com.jjswigut.stonks.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TickerText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun PriceText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun NameText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyMedium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun SubtitleText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun ValueText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign
    )
}

@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelLarge
    )
}
