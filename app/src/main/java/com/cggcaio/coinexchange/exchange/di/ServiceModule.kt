package com.cggcaio.coinexchange.exchange.di

import com.cggcaio.coinexchange.exchange.data.service.ExchangeService
import com.cggcaio.network.ApiClient
import com.cggcaio.network.BuildConfig
import com.cggcaio.network.createService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun providesExchangeRatesService(apiClient: ApiClient): ExchangeService =
        apiClient.createService(BuildConfig.BASE_URL, ExchangeService::class.java)
}
