package com.jjswigut.stonks.repository

import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.data.network.ktor.StonkServiceClient
import com.jjswigut.stonks.data.network.ktor.StonkServiceError.MalformedJsonError
import com.jjswigut.stonks.data.network.ktor.StonkServiceImpl
import com.jjswigut.stonks.data.network.ktor.StonkServiceMockEngine
import com.jjswigut.stonks.data.network.ktor.StonkServiceTestData
import com.jjswigut.stonks.data.network.utils.Either.Success
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class StonkRepositoryTest {

    private lateinit var stonkRepository: StonkRepository

    private val mockEngine = StonkServiceMockEngine().getEngine(StonkServiceTestData.stonksUrl)

    @Before
    fun setUp() {
        stonkRepository = StonkRepository(
            stonkService = StonkServiceImpl(
                client = StonkServiceClient(mockEngine),
            ),
        )
    }

    @Test
    fun `StonkDTOs are correctly transformed to Stonks`() = runBlocking {
        val expectedStonk = Stonk(
            ticker = "^GSPC",
            name = "S&P 500",
            currency = "USD",
            currentPrice = "$3,181",
            quantity = null,
            totalValue = "$0",
            currentPriceTimestamp = "6:10 AM"
        )
        val response = stonkRepository.getStonks()
        require(response is Success)
        assertEquals(response.value.first(), expectedStonk)
    }
}