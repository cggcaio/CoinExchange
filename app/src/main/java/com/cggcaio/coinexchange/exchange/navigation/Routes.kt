package com.cggcaio.coinexchange.exchange.navigation

import kotlinx.serialization.Serializable

@Serializable
object ExchangeGraph

@Serializable
object ExchangeList

@Serializable
data class ExchangeCurrentRate(
    val id: String,
)
