package com.jjswigut.stonks.feature.stonklist

import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.data.network.ktor.StonkServiceError
import com.jjswigut.stonks.data.network.utils.Either
import com.jjswigut.stonks.data.network.utils.Either.Companion
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListEffect

object StonkListViewModelTestData {

    val successfulStonkList = listOf(Stonk(
        ticker = "^GSPC",
        name = "S&P 500",
        currency = "USD",
        currentPrice = "$3,181",
        quantity = null,
        totalValue = "$0",
        currentPriceTimestamp = "6:10 AM"
    ))

    val stonkErrorExpectedEffect = StonkListEffect.ShowError(StonkServiceError.GeneralError)
}