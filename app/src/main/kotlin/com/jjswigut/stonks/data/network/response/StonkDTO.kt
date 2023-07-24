package com.jjswigut.stonks.data.network.response

import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.ui.utils.formatCentsAsCurrency
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * A data class representing a Stonk Data Transfer Object (DTO).
 *
 * @param ticker The ticker symbol of the stonk.
 * @param name The name of the stonk.
 * @param currency The currency of the stonk's price.
 * @param current_price_cents The current price of the stonk in cents.
 * @param quantity The quantity of the stonk held, or null if not specified.
 * @param current_price_timestamp The timestamp when the current price was fetched.
 */
@Serializable
data class StonkDTO(
    val ticker: String,
    val name: String,
    val currency: String,
    val current_price_cents: Int,
    val quantity: Int?,
    val current_price_timestamp: Long
)

fun StonkDTO.toStonk(): Stonk {
    return Stonk(
        ticker = ticker,
        name = name,
        currency = currency,
        currentPrice = current_price_cents.formatCentsAsCurrency(),
        quantity = quantity,
        totalValue = quantity?.times(current_price_cents)?.formatCentsAsCurrency(),
        lastUpdatedTime = formatTimeStamp(current_price_timestamp)
    )
}

private fun formatTimeStamp(timeStamp: Long): String {
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault()).format(
        formatter
    )
}
