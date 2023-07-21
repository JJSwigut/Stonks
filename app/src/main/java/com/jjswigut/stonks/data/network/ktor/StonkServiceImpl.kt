package com.jjswigut.stonks.data.network.ktor

import com.jjswigut.stonks.data.network.ktor.StonkServiceError.GeneralError
import com.jjswigut.stonks.data.network.ktor.StonkServiceError.MalformedJsonError
import com.jjswigut.stonks.data.network.response.StonksResponse
import com.jjswigut.stonks.data.network.utils.Either
import com.jjswigut.stonks.dispatchers.Dispatcher
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class StonkServiceImpl(
    private val client: StonkServiceClient,
) : StonkService {

    override suspend fun getStonks(): Either<StonksResponse, StonkServiceError> {
        return runCatching {
            client.getStonks().body<StonksResponse>()
        }.fold(onSuccess = { response ->
            Either.success(response)
        }, onFailure = { error ->
            // parse out the error, this could definitely be more robust in a production app
            val returnedError = if (error is JsonConvertException) {
                MalformedJsonError
            } else {
                GeneralError
            }
            Either.failure(returnedError)
        })

    }
}