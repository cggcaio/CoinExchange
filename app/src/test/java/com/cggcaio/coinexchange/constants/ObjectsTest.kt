package com.cggcaio.coinexchange.constants

import com.cggcaio.coinexchange.exchange.data.model.ExchangeDetailsResponse
import com.cggcaio.coinexchange.exchange.data.model.ExchangeResponse
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.network.error.model.CustomNetworkException

object ObjectsTest {
    val exchange_1_api =
        ExchangeResponse(
            id = "BINANCE",
            name = "Binance",
            volume1DayUsd = 111738443.25,
        )

    val exchange_2_api =
        ExchangeResponse(
            id = "MERCADOBITCOIN",
            name = "Mercado Bitcoin",
            volume1DayUsd = 3412738443.25,
        )

    val exchangeList_response = listOf(exchange_1_api, exchange_2_api)

    val exchange_1 =
        Exchange(
            id = "BINANCE",
            name = "Binance",
            volume1DayUsd = "$111,738,443.25",
        )

    val exchange_2 =
        Exchange(
            id = "MERCADOBITCOIN",
            name = "Mercado Bitcoin",
            volume1DayUsd = "$3,412,738,443.25",
        )

    val exchangeList = listOf(exchange_1, exchange_2)

    val exchangeDetails_api =
        ExchangeDetailsResponse(
            exchangeId = "BINANCE",
            website = "https://www.binance.com/",
            name = "Binance",
            dataQuoteStart = "2017-03-15T00:00:00.0000000Z",
            dataQuoteEnd = "2025-01-29T00:00:00.0000000Z",
            dataOrderStart = "2017-03-14T00:00:00.0000000Z",
            dataOrderEnd = "2025-01-30T00:00:00.0000000Z",
            dataTradeStart = "2017-03-07T00:00:00.0000000Z",
            dataTradeEnd = "2025-01-31T00:00:00.0000000Z",
            volume1DayUsd = 111738443.25,
            volume1HrsUsd = 112738443.25,
            volume1MthUsd = 113738443.25,
        )

    val exchangeDetails_1 =
        ExchangeDetails(
            exchangeId = "BINANCE",
            website = "https://www.binance.com/",
            name = "Binance",
            dataQuoteStart = "15/03/2017",
            dataQuoteEnd = "29/01/2025",
            dataOrderStart = "14/03/2017",
            dataOrderEnd = "30/01/2025",
            dataTradeStart = "07/03/2017",
            dataTradeEnd = "31/01/2025",
            volume1DayUsd = "$111,738,443.25",
            volume1HrsUsd = "$112,738,443.25",
            volume1MthUsd = "$113,738,443.25",
        )

    val customNetworkException =
        CustomNetworkException(
            code = 422,
            friendlyMessage = "Erro de conexão",
            message = "Error",
        )
}
