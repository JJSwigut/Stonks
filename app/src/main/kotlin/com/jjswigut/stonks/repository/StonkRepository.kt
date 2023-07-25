package com.jjswigut.stonks.repository

import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.data.network.ktor.StonkServiceError
import com.jjswigut.stonks.data.network.utils.Either

/**
 * A repository interface for retrieving stock data.
 *
 * This provides an abstraction over the data layer, and can be implemented to provide data from
 * various sources, such as network, cache, or database.
 */
interface StonkRepository {
    suspend fun getStonks(): Either<List<Stonk>, StonkServiceError>
}
