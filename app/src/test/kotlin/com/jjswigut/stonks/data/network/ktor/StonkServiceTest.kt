package com.jjswigut.stonks.data.network.ktor

import com.jjswigut.stonks.data.network.ktor.StonkServiceError.GeneralError
import com.jjswigut.stonks.data.network.ktor.StonkServiceError.MalformedJsonError
import com.jjswigut.stonks.data.network.utils.Either.Failure
import com.jjswigut.stonks.data.network.utils.Either.Success
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StonkServiceTest {
    private val mockEngine = StonkServiceMockEngine()

    @Test
    fun `malformed Json yields correct MalformedJsonError`() = runBlocking {
        val response = createService(StonkServiceTestData.stonksMalformedUrl).getStonks()

        require(response is Failure)
        assert(response.error is MalformedJsonError)
    }

    @Test
    fun `empty Json yields no results`() = runBlocking {
        val response = createService(StonkServiceTestData.stonksEmptyUrl).getStonks()

        require(response is Success)
        assert(response.value.stocks.isEmpty())
    }

    @Test
    fun `regular Json yields success`() = runBlocking {
        val response = createService(StonkServiceTestData.stonksUrl).getStonks()

        require(response is Success)
        assert(response.value.stocks.size == 1)
    }

    @Test
    fun `network error yields GeneralError`() = runBlocking {
        val response = createService(StonkServiceTestData.stonksErrorUrl).getStonks()

        require(response is Failure)
        assert(response.error is GeneralError)
    }

    private fun createService(url: String): StonkService {
        return StonkServiceImpl(
            client = StonkServiceClient(mockEngine.getEngine(url))
        )
    }
}
