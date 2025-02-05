package com.cggcaio.coinexchange.exchange.data.mapper

import com.cggcaio.coinexchange.core.base.BaseMapper
import com.cggcaio.coinexchange.core.extension.convertDateFormat
import com.cggcaio.coinexchange.core.extension.formatToDollar
import com.cggcaio.coinexchange.exchange.data.model.ExchangeDetailsResponse
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails

object ExchangeDetailsMapper : BaseMapper<ExchangeDetailsResponse, ExchangeDetails>() {
    override fun transformTo(source: ExchangeDetailsResponse): ExchangeDetails =
        ExchangeDetails(
            exchangeId = source.exchangeId,
            name = source.name,
            website = source.website,
            dataQuoteStart = source.dataQuoteStart?.convertDateFormat(),
            dataQuoteEnd = source.dataQuoteEnd?.convertDateFormat(),
            dataOrderStart = source.dataOrderStart?.convertDateFormat(),
            dataOrderEnd = source.dataOrderEnd?.convertDateFormat(),
            dataTradeStart = source.dataTradeStart?.convertDateFormat(),
            dataTradeEnd = source.dataTradeEnd?.convertDateFormat(),
            volume1HrsUsd = source.volume1HrsUsd?.formatToDollar(),
            volume1DayUsd = source.volume1DayUsd?.formatToDollar(),
            volume1MthUsd = source.volume1MthUsd?.formatToDollar(),
        )
}
