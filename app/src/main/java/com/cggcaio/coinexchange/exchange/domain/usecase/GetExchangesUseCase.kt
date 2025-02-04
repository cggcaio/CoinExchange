package com.cggcaio.coinexchange.exchange.domain.usecase

import com.cggcaio.coinexchange.core.base.BaseUseCase
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetExchangesUseCase
    @Inject
    constructor(
        private val _repository: ExchangeRepository,
    ) : BaseUseCase<List<Exchange>, BaseUseCase.None>() {
        override fun doWork(params: None): Flow<List<Exchange>?> =
            flow {
                emit(_repository.getExchanges())
            }
    }
