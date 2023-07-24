package com.jjswigut.stonks.data.models

/**
 * A data class representing a stonk.
 *
 * @param ticker The ticker symbol of the stonk.
 * @param name The name of the stonk.
 * @param currency The currency of the stonk's price.
 * @param currentPrice The current price of the stonk.
 * @param quantity The quantity of the stonk held, or null if not specified.
 * @param totalValue The total value of the stonk held.
 * @param lastUpdatedTime The last time the stonk's price was updated.
 */
data class Stonk(
    val ticker: String,
    val name: String,
    val currency: String,
    val currentPrice: String,
    val quantity: Int?,
    val totalValue: String?,
    val lastUpdatedTime: String
)
