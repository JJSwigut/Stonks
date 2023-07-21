package com.jjswigut.stonks.repository

import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.data.network.ktor.StonkService
import com.jjswigut.stonks.data.network.ktor.StonkServiceError
import com.jjswigut.stonks.data.network.response.toStonk
import com.jjswigut.stonks.data.network.utils.Either
import com.jjswigut.stonks.data.network.utils.processSuccess

class StonkRepository(
    private val stonkService: StonkService,
) {
    suspend fun getStonks(): Either<List<Stonk>, StonkServiceError> {
        return stonkService.getStonks().processSuccess { response ->
            response.stocks.map { it.toStonk() }
        }
    }
}