package com.jjswigut.stonks.data.network.response

import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.ui.utils.formatCentsToCurrency
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StonkDTO(
    val ticker: String,
    val name: String,
    val currency: String,
    @SerialName("current_price_cents") val currentPriceCents: Int,
    val quantity: Int?,
    @SerialName("current_price_timestamp") val currentPriceTimestamp: Long
)

fun StonkDTO.toStonk(): Stonk {
    return Stonk(
        ticker = ticker,
        name = name,
        currency = currency,
        currentPrice = currentPriceCents.formatCentsToCurrency(),
        quantity = quantity,
        totalValue = (quantity?.times(currentPriceCents) ?: 0).formatCentsToCurrency(),
        currentPriceTimestamp = formatTimeStamp(currentPriceTimestamp)
    )
}

private fun formatTimeStamp(timeStamp: Long): String {
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault()).format(
        formatter
    )
}