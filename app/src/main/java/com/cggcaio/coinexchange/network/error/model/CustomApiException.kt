package com.cggcaio.coinexchange.network.error.model

data class CustomApiException(
    val code: Int,
    override val message: String,
    val friendlyMessage: String,
) : Exception(message)
