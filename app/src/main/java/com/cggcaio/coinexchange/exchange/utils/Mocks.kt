package com.cggcaio.coinexchange.exchange.utils

import com.cggcaio.coinexchange.exchange.domain.model.Exchange

object Mocks {
    val exchangeList =
        listOf(
            Exchange(
                id = "BINANCE",
                name = "Binance",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "KRAKEN",
                name = "Kraken",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "COINBASE",
                name = "Coinbase Pro (GDAX)",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "BITSTAMP",
                name = "Bitstamp Ltd.",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "ECB",
                name = "European Central Bank",
                volume1DayUsd = "$1,999,999.00",
            ),
        )
}
