package com.jjswigut.stonks.data.network.ktor

import com.jjswigut.stonks.data.network.response.StonksResponse
import com.jjswigut.stonks.data.network.utils.Either

/**
 * An interface for the stonk service that defines the operations that the service should support.
 */
interface StonkService {
    suspend fun getStonks(): Either<StonksResponse, StonkServiceError>
}
