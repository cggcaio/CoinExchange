package com.cggcaio.coinexchange.exchange.domain.model

data class ExchangeDetails(
    val exchangeId: String?,
    val website: String?,
    val name: String?,
    val volume1HrsUsd: Double?,
    val volume1DayUsd: Double?,
    val volume1MthUsd: Double?,
)
