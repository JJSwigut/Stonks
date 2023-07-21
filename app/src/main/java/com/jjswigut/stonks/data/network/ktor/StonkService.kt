package com.jjswigut.stonks.data.network.ktor

import com.jjswigut.stonks.data.network.response.StonksResponse
import com.jjswigut.stonks.data.network.utils.Either

interface StonkService {
    suspend fun getStonks(): Either<StonksResponse, StonkServiceError>
}