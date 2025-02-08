package com.cggcaio.coinexchange.network.error.model

data class CustomNetworkException(
    val code: Int,
    override val message: String,
    val friendlyMessage: String,
) : Exception(message)
