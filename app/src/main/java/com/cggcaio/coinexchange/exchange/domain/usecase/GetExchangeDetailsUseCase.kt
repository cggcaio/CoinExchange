package com.cggcaio.coinexchange.exchange.domain.usecase

import com.cggcaio.coinexchange.core.base.BaseUseCase
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.coinexchange.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetExchangeDetailsUseCase
    @Inject
    constructor(
        private val _repository: ExchangeRepository,
    ) : BaseUseCase<ExchangeDetails, GetExchangeDetailsUseCase.Params>() {
        data class Params(
            val id: String,
        )

        override fun doWork(params: Params): Flow<ExchangeDetails?> =
            flow {
                emit(_repository.getExchangeDetails(id = params.id))
            }
    }
