package com.cggcaio.coinexchange.network.di

import com.cggcaio.coinexchange.network.ApiClient
import com.cggcaio.coinexchange.network.ApiClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiClient(): ApiClient = ApiClientImpl()
}
