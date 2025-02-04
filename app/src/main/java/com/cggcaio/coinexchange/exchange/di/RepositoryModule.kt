package com.cggcaio.coinexchange.exchange.di

import com.cggcaio.coinexchange.exchange.data.repository.ExchangeRepositoryImpl
import com.cggcaio.coinexchange.exchange.domain.repository.ExchangeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideExchangeRepository(repository: ExchangeRepositoryImpl): ExchangeRepository = repository
}
