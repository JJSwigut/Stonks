package com.jjswigut.stonks.feature.stonklist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jjswigut.stonks.R.dimen
import com.jjswigut.stonks.R.plurals
import com.jjswigut.stonks.R.string
import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.ui.components.NameText
import com.jjswigut.stonks.ui.components.PriceText
import com.jjswigut.stonks.ui.components.SubtitleText
import com.jjswigut.stonks.ui.components.TickerText
import com.jjswigut.stonks.ui.components.ValueText

@Composable
fun StonkListItem(
    modifier: Modifier = Modifier,
    stonk: Stonk
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = dimen.padding_default_half))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TickerText(text = stonk.ticker)
                PriceText(text = stonk.currentPrice)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NameText(
                    modifier = Modifier.weight(1f),
                    text = stonk.name
                )
                stonk.quantity?.let { shareQuantity ->
                    ValueText(
                        modifier = Modifier.weight(1f),
                        text = pluralStringResource(
                            id = plurals.share_amount,
                            count = shareQuantity,
                            shareQuantity.toString()
                        ),
                        textAlign = TextAlign.End
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    SubtitleText(text = stringResource(id = string.stonk_item_last_updated))
                    ValueText(text = stonk.lastUpdatedTime)
                }
                stonk.totalValue?.let { value ->
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        SubtitleText(text = stringResource(id = string.stonk_item_value))
                        ValueText(text = value)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun StonkItemPreview() {
    StonkListItem(
        stonk = Stonk(
            ticker = "TSLA",
            name = "Tesla",
            currency = "USD",
            currentPrice = "123.45",
            lastUpdatedTime = "6:10AM",
            quantity = 1,
            totalValue = "123.45"
        )
    )
}
