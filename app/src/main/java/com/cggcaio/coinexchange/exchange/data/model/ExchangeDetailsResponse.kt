package com.cggcaio.coinexchange.exchange.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeDetailsResponse(
    @Json(name = "exchange_id")
    val exchangeId: String?,
    val website: String?,
    val name: String?,
    @Json(name = "data_quote_start")
    val dataQuoteStart: String?,
    @Json(name = "data_quote_end")
    val dataQuoteEnd: String?,
    @Json(name = "data_orderbook_start")
    val dataOrderStart: String?,
    @Json(name = "data_orderbook_end")
    val dataOrderEnd: String?,
    @Json(name = "data_trade_start")
    val dataTradeStart: String?,
    @Json(name = "data_trade_end")
    val dataTradeEnd: String?,
    @Json(name = "volume_1hrs_usd")
    val volume1HrsUsd: Double?,
    @Json(name = "volume_1day_usd")
    val volume1DayUsd: Double?,
    @Json(name = "volume_1mth_usd")
    val volume1MthUsd: Double?,
)
