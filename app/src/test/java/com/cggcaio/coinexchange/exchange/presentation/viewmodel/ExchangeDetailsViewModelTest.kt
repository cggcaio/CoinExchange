package com.cggcaio.coinexchange.exchange.presentation.viewmodel

import com.cggcaio.coinexchange.constants.ObjectsTest.customNetworkException
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeDetails_1
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.ERROR
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.LOADING
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.NONE
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.SUCCESS
import com.cggcaio.coinexchange.exchange.domain.usecase.GetExchangeDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ExchangeDetailsViewModelTest {
    private lateinit var viewModel: ExchangeDetailsViewModel
    private val mockGetExchangeDetailsUseCase: GetExchangeDetailsUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ExchangeDetailsViewModel(mockGetExchangeDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getExchangeDetails success`() {
        coEvery { mockGetExchangeDetailsUseCase.produce(any()) } returns flow { emit(exchangeDetails_1) }

        runTest {
            viewModel.getExchangeDetails("BINANCE")
            testDispatcher.scheduler.advanceUntilIdle()

            assertEquals(SUCCESS, viewModel.requestStatus.value)
            assertEquals(exchangeDetails_1, viewModel.exchangeDetails.value)
        }

        coVerify(exactly = 1) { mockGetExchangeDetailsUseCase.produce(any()) }
    }

    @Test
    fun `test getExchangeDetails generic error`() {
        coEvery { mockGetExchangeDetailsUseCase.produce(any()) } returns flow { throw Exception("Generic Error") }

        runTest {
            viewModel.getExchangeDetails("BINANCE")
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(ERROR, viewModel.requestStatus.value)
        }

        coVerify(exactly = 1) { mockGetExchangeDetailsUseCase.produce(any()) }
    }

    @Test
    fun `test getExchangeDetails network error`() {
        coEvery { mockGetExchangeDetailsUseCase.produce(any()) } returns flow { throw customNetworkException }

        runTest {
            viewModel.getExchangeDetails("BINANCE")
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(ERROR, viewModel.requestStatus.value)
            assertEquals(customNetworkException.friendlyMessage, viewModel.error.value)
        }

        coVerify(exactly = 1) { mockGetExchangeDetailsUseCase.produce(any()) }
    }

    @Test
    fun `test getExchangeDetails loading state`() {
        coEvery { mockGetExchangeDetailsUseCase.produce(any()) } returns flow { emit(exchangeDetails_1) }

        runTest {
            assertEquals(NONE, viewModel.requestStatus.value)
            viewModel.getExchangeDetails(id = "BINANCE")
            assertEquals(LOADING, viewModel.requestStatus.value)
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(SUCCESS, viewModel.requestStatus.value)
        }

        coVerify(exactly = 1) { mockGetExchangeDetailsUseCase.produce(any()) }
    }
}
