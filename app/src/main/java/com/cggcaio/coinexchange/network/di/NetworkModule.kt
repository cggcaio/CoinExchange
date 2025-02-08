package com.cggcaio.coinexchange.network.di

import android.content.Context
import com.cggcaio.coinexchange.network.ApiClient
import com.cggcaio.coinexchange.network.ApiClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiClient(
        @ApplicationContext context: Context,
    ): ApiClient = ApiClientImpl(context)
}
