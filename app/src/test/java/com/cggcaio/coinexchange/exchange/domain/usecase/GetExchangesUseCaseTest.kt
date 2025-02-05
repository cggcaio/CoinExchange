package com.cggcaio.coinexchange.exchange.domain.usecase

import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeList
import com.cggcaio.coinexchange.core.base.BaseUseCase
import com.cggcaio.coinexchange.exchange.domain.repository.ExchangeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.fail
import org.junit.Test

class GetExchangesUseCaseTest {
    private val _repository = mockkClass(ExchangeRepository::class)
    private val _getExchangesUseCase = GetExchangesUseCase(_repository)

    @Test
    fun `test doWork should return list of exchanges when repository returns data`() {
        coEvery { _repository.getExchanges() } returns exchangeList

        runTest {
            _getExchangesUseCase
                .produce(params = BaseUseCase.None)
                .map { exchanges ->
                    assertEquals(exchangeList, exchanges)
                }.catch {
                    fail()
                }.launchIn(this)
        }

        coVerify(exactly = 1) { _repository.getExchanges() }
    }
}
