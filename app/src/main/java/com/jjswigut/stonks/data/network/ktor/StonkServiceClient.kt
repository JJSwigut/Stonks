package com.jjswigut.stonks.data.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class StonkServiceClient(engine: HttpClientEngine) {
    private val client = HttpClient(engine) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    suspend fun getStonks() = client.get(stonks_url)

    companion object {
        private const val stonks_url =
            "https://storage.googleapis.com/cash-homework/cash-stocks-api/portfolio.json"
    }
}