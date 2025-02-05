package com.cggcaio.coinexchange.exchange.domain.usecase

import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeDetails
import com.cggcaio.coinexchange.exchange.domain.repository.ExchangeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetExchangeDetailsUseCaseTest {
    private val _repository = mockkClass(ExchangeRepository::class)
    private val _getExchangeDetailsUseCase = GetExchangeDetailsUseCase(_repository)

    @Test
    fun `test doWork should return exchange details when repository returns data`() {
        coEvery { _repository.getExchangeDetails(id = any()) } returns exchangeDetails

        runTest {
            _getExchangeDetailsUseCase
                .produce(params = GetExchangeDetailsUseCase.Params(id = "id"))
                .map { details ->
                    assertEquals(exchangeDetails, details)
                }.catch {
                    fail()
                }.launchIn(this)
        }

        coVerify(exactly = 1) { _repository.getExchangeDetails(any()) }
    }
}
