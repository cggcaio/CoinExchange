package com.cggcaio.coinexchange.network

import android.net.Uri

fun <T> ApiClient.createService(
    url: String,
    clazz: Class<T>,
): T {
    val builder = Uri.Builder()
    builder.scheme("https").authority(url)
    configure(builder.toString())
    return getRetrofit().create(clazz)
}
