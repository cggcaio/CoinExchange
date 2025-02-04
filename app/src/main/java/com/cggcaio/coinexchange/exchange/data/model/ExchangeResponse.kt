package com.cggcaio.coinexchange.exchange.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeResponse(
    @Json(name = "exchange_id")
    val id: String,
    val name: String?,
    @Json(name = "volume_1day_usd")
    val volume1DayUsd: Double?,
)
