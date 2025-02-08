package com.cggcaio.coinexchange.exchange.presentation.viewmodel

import com.cggcaio.coinexchange.constants.ObjectsTest.customNetworkException
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeList
import com.cggcaio.coinexchange.constants.ObjectsTest.exchange_1
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.EMPTY_LIST
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.ERROR
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.LOADING
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.NONE
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.SUCCESS
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.domain.usecase.GetExchangesUseCase
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

class ExchangeListViewModelTest {
    private lateinit var viewModel: ExchangeListViewModel
    private val mockGetExchangesUseCase: GetExchangesUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ExchangeListViewModel(mockGetExchangesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getExchanges success`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { emit(exchangeList) }

        runTest {
            viewModel.getExchanges()
            testDispatcher.scheduler.advanceUntilIdle()

            assertEquals(SUCCESS, viewModel.listStatus.value)
            assertEquals(exchangeList, viewModel.visibleExchanges.value)
        }

        coVerify(exactly = 1) { mockGetExchangesUseCase.produce(any()) }
    }

    @Test
    fun `test getExchanges empty list`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { emit(emptyList()) }

        runTest {
            viewModel.getExchanges()
            testDispatcher.scheduler.advanceUntilIdle()

            assertEquals(EMPTY_LIST, viewModel.listStatus.value)
        }

        coVerify(exactly = 1) { mockGetExchangesUseCase.produce(any()) }
    }

    @Test
    fun `test getExchanges generic error`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { throw Exception("Generic Error") }

        runTest {
            viewModel.getExchanges()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(ERROR, viewModel.listStatus.value)
        }

        coVerify(exactly = 1) { mockGetExchangesUseCase.produce(any()) }
    }

    @Test
    fun `test getExchanges network error`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { throw customNetworkException }

        runTest {
            viewModel.getExchanges()
            testDispatcher.scheduler.advanceUntilIdle()

            assertEquals(ERROR, viewModel.listStatus.value)
            assertEquals(customNetworkException.friendlyMessage, viewModel.error.value)
        }

        coVerify(exactly = 1) { mockGetExchangesUseCase.produce(any()) }
    }

    @Test
    fun `test getExchanges loading state`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { emit(exchangeList) }

        runTest {
            assertEquals(NONE, viewModel.listStatus.value)
            viewModel.getExchanges()
            assertEquals(LOADING, viewModel.listStatus.value)
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(SUCCESS, viewModel.listStatus.value)
        }

        coVerify(exactly = 1) { mockGetExchangesUseCase.produce(any()) }
    }

    @Test
    fun `test filterExchanges with results`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { emit(exchangeList) }

        runTest {
            viewModel.getExchanges()
            testDispatcher.scheduler.advanceUntilIdle()
            viewModel.filterExchanges(query = "BINANCE")

            assertEquals(listOf(exchange_1), viewModel.visibleExchanges.value)
            assertEquals(SUCCESS, viewModel.listStatus.value)
        }
    }

    @Test
    fun `test filterExchanges with no results`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { emit(exchangeList) }

        runTest {
            viewModel.getExchanges()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(exchangeList, viewModel.visibleExchanges.value)
            viewModel.filterExchanges("INVALID")
            assertEquals(emptyList<Exchange>(), viewModel.visibleExchanges.value)
            assertEquals(EMPTY_LIST, viewModel.listStatus.value)
        }
    }

    @Test
    fun `test filterExchanges reset to full list when query is empty`() {
        coEvery { mockGetExchangesUseCase.produce(any()) } returns flow { emit(exchangeList) }

        runTest {
            viewModel.getExchanges()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(exchangeList, viewModel.visibleExchanges.value)
            viewModel.filterExchanges("INVALID")
            assertEquals(emptyList<Exchange>(), viewModel.visibleExchanges.value)
            assertEquals(EMPTY_LIST, viewModel.listStatus.value)
            viewModel.filterExchanges("")
            assertEquals(exchangeList, viewModel.visibleExchanges.value)
            assertEquals(SUCCESS, viewModel.listStatus.value)
        }
    }
}
