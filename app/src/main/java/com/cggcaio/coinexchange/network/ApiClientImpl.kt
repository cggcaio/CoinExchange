package com.cggcaio.coinexchange.network

import com.cggcaio.coinexchange.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientImpl : ApiClient {
    companion object {
        private const val KEY = BuildConfig.API_KEY
        private const val X_COINAPI_KEY = "X-CoinAPI-Key"
        private const val OKHTTP_TIMEOUT = 120L
    }

    private val _tokenInterceptor = { chain: Interceptor.Chain ->
        val request =
            chain
                .request()
                .newBuilder()
                .header(X_COINAPI_KEY, KEY)
                .build()
        chain.proceed(request)
    }

    private var _retrofit: Retrofit? = null

    override fun getRetrofit(): Retrofit = _retrofit ?: throw IllegalStateException("invalid application state")

    override fun configure(baseUrl: String) {
        _retrofit =
            Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(createClient())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.apply {
            addInterceptor(_tokenInterceptor)
            connectTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
        }
        return client.build()
    }
}
