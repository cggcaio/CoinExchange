package com.cggcaio.coinexchange.exchange.domain.repository

import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails

interface ExchangeRepository {
    suspend fun getExchanges(): List<Exchange>

    suspend fun getExchangeCurrentRate(id: String): ExchangeDetails
}
