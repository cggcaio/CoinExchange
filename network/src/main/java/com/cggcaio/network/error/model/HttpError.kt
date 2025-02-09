package com.cggcaio.network.error.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HttpError(
    val error: String? = null,
)
