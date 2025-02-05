package com.cggcaio.coinexchange.exchange.data.mapper

import com.cggcaio.coinexchange.constants.ObjectsTest.exchange_1
import com.cggcaio.coinexchange.constants.ObjectsTest.exchange_1_api
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExchangeMapperTest {
    @Test
    fun `test transformTo should map ExchangeResponse to Exchange correctly`() {
        val result = ExchangeMapper.transformTo(exchange_1_api)
        assertEquals(exchange_1, result)
    }

    @Test
    fun `test transformTo should use default name when name is null`() {
        val exchangeResponse = exchange_1_api.copy(name = null)
        val result = ExchangeMapper.transformTo(exchangeResponse)
        assertEquals(exchange_1.copy(name = "Corretora sem nome"), result)
    }

    @Test
    fun `test transformTo should use default volume when volume1DayUsd is null`() {
        val exchangeResponse = exchange_1_api.copy(volume1DayUsd = null)
        val result = ExchangeMapper.transformTo(exchangeResponse)
        assertEquals(exchange_1.copy(volume1DayUsd = "$0.0"), result)
    }
}
