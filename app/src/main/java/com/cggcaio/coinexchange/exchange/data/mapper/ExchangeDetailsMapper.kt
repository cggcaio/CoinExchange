package com.cggcaio.coinexchange.exchange.data.mapper

import com.cggcaio.coinexchange.core.base.BaseMapper
import com.cggcaio.coinexchange.exchange.data.model.ExchangeDetailsResponse
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails

object ExchangeDetailsMapper : BaseMapper<ExchangeDetailsResponse, ExchangeDetails>() {
    override fun transformTo(source: ExchangeDetailsResponse): ExchangeDetails =
        ExchangeDetails(
            exchangeId = source.exchangeId,
            name = source.name,
            website = source.website,
            volume1HrsUsd = source.volume1HrsUsd,
            volume1DayUsd = source.volume1DayUsd,
            volume1MthUsd = source.volume1MthUsd,
        )
}
