package com.cggcaio.coinexchange.network

import retrofit2.Retrofit

interface ApiClient {
    fun getRetrofit(): Retrofit

    fun configure(baseUrl: String)
}
