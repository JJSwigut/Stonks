package com.jjswigut.stonks.feature.stonklist

import app.cash.turbine.test
import com.jjswigut.stonks.data.network.ktor.StonkServiceError.GeneralError
import com.jjswigut.stonks.data.network.utils.Either
import com.jjswigut.stonks.dispatchers.Dispatcher
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListAction.RefreshStonks
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListViewState
import com.jjswigut.stonks.repository.StonkRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StonkListViewModelTest {

    private lateinit var viewModel: StonkListViewModel

    private val stonkRepository = mockk<StonkRepository>()
    private val dispatcher = mockk<Dispatcher>()

    @Before
    fun before() {
        every { dispatcher.io } returns Dispatchers.IO
        viewModel = StonkListViewModel(
            stonkRepository = stonkRepository,
            dispatcher = dispatcher
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