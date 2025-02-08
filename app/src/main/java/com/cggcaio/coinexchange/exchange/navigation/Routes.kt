package com.cggcaio.coinexchange.exchange.navigation

import kotlinx.serialization.Serializable

@Serializable
object ExchangeGraph

@Serializable
object ExchangeListRoute

@Serializable
data class ExchangeDetailsRoute(
    val id: String,
)
