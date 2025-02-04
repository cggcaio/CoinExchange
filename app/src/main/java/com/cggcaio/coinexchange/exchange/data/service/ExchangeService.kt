package com.cggcaio.coinexchange.exchange.data.service

import com.cggcaio.coinexchange.exchange.data.model.ExchangeDetailsResponse
import com.cggcaio.coinexchange.exchange.data.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeService {
    @GET("/v1/exchanges")
    suspend fun getAllExchanges(): List<ExchangeResponse>

    @GET("/v1/exchanges/{exchange_id}")
    suspend fun getExchangeRates(
        @Path("exchange_id") id: String,
    ): List<ExchangeDetailsResponse>
}
