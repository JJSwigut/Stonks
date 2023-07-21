package com.jjswigut.stonks.data.models

data class Stonk(
    val ticker: String,
    val name: String,
    val currency: String,
    val currentPrice: String,
    val quantity: Int?,
    val totalValue: String,
    val currentPriceTimestamp: String
)