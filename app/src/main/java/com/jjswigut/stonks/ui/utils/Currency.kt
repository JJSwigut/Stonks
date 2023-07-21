package com.jjswigut.stonks.ui.utils

import java.text.NumberFormat
import java.util.Currency


fun Int.formatCentsToCurrency(): String {
    val format = NumberFormat.getCurrencyInstance()

    format.maximumFractionDigits = 2
    format.minimumFractionDigits = 0

    // All the data is in USD. Normally I would pass in currency code for a more robust solution but
    // chose not to on the basis that future-proofing is often just over-engineering.
    format.currency = Currency.getInstance("USD")

    val dollars  = this / 100

    return format.format(dollars)
}