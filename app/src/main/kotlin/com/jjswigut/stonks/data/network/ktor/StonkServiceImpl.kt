package com.jjswigut.stonks.data.network.ktor

import com.jjswigut.stonks.data.network.ktor.StonkServiceError.GeneralError
import com.jjswigut.stonks.data.network.ktor.StonkServiceError.MalformedJsonError
import com.jjswigut.stonks.data.network.response.StonksResponse
import com.jjswigut.stonks.data.network.utils.Either
import io.ktor.client.call.body
import io.ktor.serialization.JsonConvertException

/**
 * A class implementing the StonkService interface.
 *
 * @param client An instance of the StonkServiceClient used for the network requests.
 */
class StonkServiceImpl(
    private val client: StonkServiceClient
) : StonkService {

    override suspend fun getStonks(): Either<StonksResponse, StonkServiceError> {
        return runCatching {
            client.getStonks().body<StonksResponse>()
        }.fold(
            onSuccess = { response ->
                Either.success(response)
            },
            onFailure = { error ->
                // parse out the error, this could definitely be more robust in a production app
                val returnedError = if (error is JsonConvertException) {
                    MalformedJsonError
                } else {
                    GeneralError
                }
                Either.failure(returnedError)
            }
        )
    }
}
