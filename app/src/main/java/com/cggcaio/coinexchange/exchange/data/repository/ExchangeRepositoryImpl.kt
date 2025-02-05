package com.cggcaio.coinexchange.exchange.data.repository

import com.cggcaio.coinexchange.exchange.data.mapper.ExchangeDetailsMapper
import com.cggcaio.coinexchange.exchange.data.mapper.ExchangeMapper
import com.cggcaio.coinexchange.exchange.data.service.ExchangeService
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.coinexchange.exchange.domain.repository.ExchangeRepository
import javax.inject.Inject

class ExchangeRepositoryImpl
    @Inject
    constructor(
        private val _exchangeService: ExchangeService,
    ) : ExchangeRepository {
        override suspend fun getExchanges(): List<Exchange> = ExchangeMapper.transformToList(source = _exchangeService.getAllExchanges())

        override suspend fun getExchangeDetails(id: String): ExchangeDetails =
            ExchangeDetailsMapper.transformTo(
                source = _exchangeService.getExchangeDetails(id = id).last(),
            )
    }
