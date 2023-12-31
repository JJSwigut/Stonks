package com.jjswigut.stonks.feature.stonklist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jjswigut.stonks.R.dimen
import com.jjswigut.stonks.R.string
import com.jjswigut.stonks.ui.components.SubtitleText
import com.jjswigut.stonks.ui.components.TitleText

@Preview
@Composable
fun StonkListEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText(
            text = stringResource(
                id = string.stonk_list_empty_text
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = dimen.padding_default)))
        SubtitleText(
            text = stringResource(
                id = string.stonk_list_empty_text_instruction
            )
        )
    }
}
