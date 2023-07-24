package com.jjswigut.stonks.feature.stonklist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jjswigut.stonks.R
import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.data.network.ktor.StonkServiceError
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListAction.RefreshStonks
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListEffect
import com.jjswigut.stonks.feature.stonklist.ui.StonkListEmptyContent
import com.jjswigut.stonks.feature.stonklist.ui.StonkListItem
import com.jjswigut.stonks.ui.components.Loader
import com.jjswigut.stonks.ui.theme.ButtonText
import com.jjswigut.stonks.ui.theme.TitleText
import com.jjswigut.stonks.ui.utils.collectAsEffect
import com.jjswigut.stonks.ui.utils.isScrollingUp
import org.koin.androidx.compose.koinViewModel

@Composable
fun StonkListScreen(
    viewModel: StonkListViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.setAction(RefreshStonks)
    }

    rememberSystemUiController().setStatusBarColor(
        Color.Transparent,
        darkIcons = !isSystemInDarkTheme()
    )

    var error by remember { mutableStateOf<StonkServiceError?>(null) }

    // we only have one effect, but this is where one-offs such as navigation will happen as well
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is StonkListEffect.ShowError -> {
                error = effect.error
            }
        }
    }

    val state = viewModel.state.collectAsState().value

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
        val listState = rememberLazyListState()

        Scaffold(
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_default))
                ) {
                    StonkList(
                        listState = listState,
                        isLoading = state.isLoading,
                        stonks = state.stonks
                    )
                    Loader(
                        modifier = Modifier.align(Alignment.Center),
                        isLoading = state.isLoading
                    )
                    ErrorDialog(
                        error = error,
                        onDismiss = { error = null }
                    )
                }
            },
            floatingActionButton = {
                RefreshFAB(
                    extended = listState.isScrollingUp(),
                    onClick = { viewModel.setAction(RefreshStonks) }
                )
            }
        )
    }
}

@Composable
private fun ErrorDialog(
    error: StonkServiceError?,
    onDismiss: () -> Unit
) {
    error?.let { e ->
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.dialog_elevation))
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_default)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TitleText(text = stringResource(id = e.messageRes))
                    Spacer(Modifier.height(dimensionResource(id = R.dimen.padding_default)))
                    Button(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.button_height))
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                        onClick = onDismiss
                    ) {
                        ButtonText(
                            text = stringResource(id = R.string.ok)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StonkList(
    listState: LazyListState,
    isLoading: Boolean,
    stonks: List<Stonk>
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (stonks.isEmpty() && !isLoading) {
            StonkListEmptyContent(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = spacedBy(dimensionResource(id = R.dimen.padding_default_quarter))
            ) {
                items(stonks.size) { index ->
                    StonkListItem(stonk = stonks[index])
                }
                item {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.stonk_list_footer_spacing)))
                }
            }
        }
    }
}

@Composable
private fun RefreshFAB(
    extended: Boolean,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_default))
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(id = R.string.list_fab_refresh)
            )
            AnimatedVisibility(visible = extended) {
                Text(
                    text = stringResource(R.string.list_fab_refresh),
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_default_half),
                            top = dimensionResource(id = R.dimen.padding_default_quarter)
                        )
                )
            }
        }
    }
}

@Preview
@Composable
private fun RefreshFABPreviewExtended() {
    RefreshFAB(extended = true, onClick = {})
}

@Preview
@Composable
private fun RefreshFABPreviewCondensed() {
    RefreshFAB(extended = false, onClick = {})
}
