package com.cggcaio.coinexchange.exchange.data.mapper

import com.cggcaio.coinexchange.exchange.data.model.ExchangeResponse
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.core.base.BaseMapper
import com.cggcaio.core.extension.formatToDollar

object ExchangeMapper : BaseMapper<ExchangeResponse, Exchange>() {
    override fun transformTo(source: ExchangeResponse): Exchange =
        Exchange(
            id = source.id,
            name = source.name ?: "Corretora sem nome",
            volume1DayUsd = source.volume1DayUsd?.formatToDollar() ?: "$0.0",
        )
}
