package com.jjswigut.stonks.feature.stonklist

import app.cash.turbine.test
import com.jjswigut.stonks.data.network.ktor.StonkServiceError.GeneralError
import com.jjswigut.stonks.data.network.utils.Either
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListAction.RefreshStonks
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListViewState
import com.jjswigut.stonks.repository.StonkRepository
import com.jjswigut.stonks.testextensions.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StonkListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: StonkListViewModel

    private val stonkRepository = mockk<StonkRepository>()

    @Before
    fun before() {
        viewModel = StonkListViewModel(
            stonkRepository = stonkRepository,
            dispatcher = coroutineTestRule.dispatcher
        )
    }

    @Test
    fun `Loader shows on first ViewState`() = runTest {
        val expected = StonkListViewState(
            stonks = emptyList(),
            isLoading = true
        )

        viewModel.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `Action_RefreshStonks returns correct ViewState on success`() = runTest {
        val expected = StonkListViewState(
            stonks = StonkListViewModelTestData.successfulStonkList,
            isLoading = false
        )
        coEvery { stonkRepository.getStonks() } returns Either.success(StonkListViewModelTestData.successfulStonkList)

        viewModel.state.test {
            viewModel.setAction(RefreshStonks)

            skipItems(2) // skips showing and hiding the loader
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `Action_RefreshStonks returns correct Effect on failure`() = runTest {
        val expected = StonkListViewModelTestData.stonkErrorExpectedEffect
        coEvery { stonkRepository.getStonks() } returns Either.failure(GeneralError)

        viewModel.effect.test {
            viewModel.setAction(RefreshStonks)

            assertEquals(expected, awaitItem())
        }
    }
}
