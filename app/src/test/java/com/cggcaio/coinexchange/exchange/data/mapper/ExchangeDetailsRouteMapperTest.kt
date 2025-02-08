package com.cggcaio.coinexchange.exchange.data.mapper

import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeDetails_1
import com.cggcaio.coinexchange.constants.ObjectsTest.exchangeDetails_api
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExchangeDetailsRouteMapperTest {
    @Test
    fun `test transformTo should map ExchangeDetailsResponse to ExchangeDetails correctly`() {
        val exchangeDetailsResponse = exchangeDetails_api
        val result = ExchangeDetailsMapper.transformTo(exchangeDetailsResponse)
        assertEquals(exchangeDetails_1, result)
    }

    @Test
    fun `test transformTo should handle null dates and format them as null`() {
        val exchangeDetailsResponse =
            exchangeDetails_api.copy(
                dataQuoteStart = null,
                dataQuoteEnd = null,
                dataOrderStart = null,
                dataOrderEnd = null,
                dataTradeStart = null,
                dataTradeEnd = null,
            )

        val result = ExchangeDetailsMapper.transformTo(exchangeDetailsResponse)
        assertEquals(
            exchangeDetails_1.copy(
                dataQuoteStart = null,
                dataQuoteEnd = null,
                dataOrderStart = null,
                dataOrderEnd = null,
                dataTradeStart = null,
                dataTradeEnd = null,
            ),
            result,
        )
    }

    @Test
    fun `test transformTo should handle null volumes and format them as null`() {
        val exchangeDetailsResponse =
            exchangeDetails_api.copy(
                volume1HrsUsd = null,
                volume1DayUsd = null,
                volume1MthUsd = null,
            )
        val result = ExchangeDetailsMapper.transformTo(exchangeDetailsResponse)
        assertEquals(
            exchangeDetails_1.copy(
                volume1HrsUsd = null,
                volume1DayUsd = null,
                volume1MthUsd = null,
            ),
            result,
        )
    }
}
