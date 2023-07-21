package com.jjswigut.stonks.data.network.ktor

import com.jjswigut.stonks.data.network.ktor.StonkServiceTestData.stonksEmptyUrl
import com.jjswigut.stonks.data.network.ktor.StonkServiceTestData.stonksErrorUrl
import com.jjswigut.stonks.data.network.ktor.StonkServiceTestData.stonksMalformedUrl
import com.jjswigut.stonks.data.network.ktor.StonkServiceTestData.stonksUrl
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpHeaders.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

class StonkServiceMockEngine() {

    private val responseHeaders = headersOf(ContentType, "application/json")

    fun getEngine(url: String) = MockEngine {
        when (url) {
            stonksUrl -> {
                respond(
                    content = StonkServiceTestData.successfulStonksJson,
                    status = HttpStatusCode.OK,
                    headers = responseHeaders
                )
            }

            stonksEmptyUrl -> {
                respond(
                    content = StonkServiceTestData.emptyStonksJson,
                    status = HttpStatusCode.OK,
                    headers = responseHeaders
                )
            }

            stonksMalformedUrl -> {
                respond(
                    content = StonkServiceTestData.malformedStonksJson,
                    status = HttpStatusCode.OK,
                    headers = responseHeaders
                )
            }

            stonksErrorUrl -> {
                respondError(HttpStatusCode.NotFound)
            }

            else -> error("Unhandled $url")
        }
    }
}