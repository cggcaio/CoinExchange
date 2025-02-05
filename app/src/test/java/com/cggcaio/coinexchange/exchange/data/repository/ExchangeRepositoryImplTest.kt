package com.cggcaio.coinexchange.exchange.data.repository

import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeDetails
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeDetails_api
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeList
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeList_response
import com.cggcaio.coinexchange.exchange.data.model.ExchangeDetailsResponse
import com.cggcaio.coinexchange.exchange.data.service.ExchangeService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExchangeRepositoryImplTest {
    private val _exchangeService = mockkClass(ExchangeService::class)
    private val _exchangeRepository = ExchangeRepositoryImpl(_exchangeService)

    @Test
    fun `test getExchanges should return list of exchanges`() =
        runBlocking {
            coEvery { _exchangeService.getAllExchanges() } returns exchangeList_response
            val result = _exchangeRepository.getExchanges()

            assertEquals(exchangeList, result)
            coVerify(exactly = 1) { _exchangeService.getAllExchanges() }
        }

    @Test(expected = RuntimeException::class)
    fun `test getExchanges should throw exception when service throws exception`() =
        runBlocking {
            coEvery { _exchangeService.getAllExchanges() } throws RuntimeException("Network error")

            val result = _exchangeRepository.getExchanges()

            assertEquals(result, RuntimeException("Network error"))
        }

    @Test
    fun `test getExchangeDetails should return exchange details`() =
        runBlocking {
            val exchangeDetailsResponse = exchangeDetails_api
            coEvery { _exchangeService.getExchangeDetails(any()) } returns
                listOf(
                    exchangeDetailsResponse,
                )

            val result = _exchangeRepository.getExchangeDetails(id = "BINANCE")

            assertEquals(exchangeDetails, result)
        }

    @Test
    fun `test getExchangeDetails should throw exception when response is empty`() =
        runBlocking {
            coEvery { _exchangeService.getExchangeDetails(id = "INVALID_ID") } returns emptyList()

            val result = _exchangeService.getExchangeDetails(id = "INVALID_ID")

            assertEquals(result, emptyList<ExchangeDetailsResponse>())
        }

    @Test(expected = RuntimeException::class)
    fun `test getExchangesDetails should throw exception when service throws exception`() =
        runBlocking {
            coEvery { _exchangeService.getExchangeDetails(any()) } throws RuntimeException("Network error")

            val result = _exchangeRepository.getExchangeDetails(id = "BINANCE")

            assertEquals(result, RuntimeException("Network error"))
        }
}
