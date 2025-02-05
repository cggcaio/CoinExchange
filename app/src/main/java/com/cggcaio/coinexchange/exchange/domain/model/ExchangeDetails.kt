package com.cggcaio.coinexchange.exchange.domain.model

data class ExchangeDetails(
    val exchangeId: String?,
    val website: String?,
    val name: String?,
    val dataQuoteStart: String?,
    val dataQuoteEnd: String?,
    val dataOrderStart: String?,
    val dataOrderEnd: String?,
    val dataTradeEnd: String?,
    val dataTradeStart: String?,
    val volume1HrsUsd: String?,
    val volume1DayUsd: String?,
    val volume1MthUsd: String?,
)
