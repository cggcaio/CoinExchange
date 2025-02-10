package com.cggcaio.coinexchange.exchange.presentation.preview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.BaseExchangeDetailsViewModel
import com.cggcaio.core.constants.RequestStatusEnum
import com.cggcaio.core.constants.RequestStatusEnum.ERROR
import com.cggcaio.core.constants.RequestStatusEnum.LOADING
import com.cggcaio.core.constants.RequestStatusEnum.SUCCESS

class ExchangeDetailsViewModelPreview : PreviewParameterProvider<BaseExchangeDetailsViewModel> {
    private val _exchangeDetails =
        ExchangeDetails(
            exchangeId = "BINANCE",
            name = "Binance",
            volume1HrsUsd = "$1,999,999.00",
            volume1DayUsd = "$1,999,999.00",
            volume1MthUsd = "$1,999,999.00",
            website = "https://www.binance.com/",
            dataQuoteStart = "01/01/2023",
            dataQuoteEnd = "31/12/2023",
            dataOrderStart = "01/01/2023",
            dataOrderEnd = "31/12/2023",
            dataTradeStart = "01/01/2023",
            dataTradeEnd = "31/12/2023",
        )
    override val values: Sequence<BaseExchangeDetailsViewModel> =
        sequenceOf(
            object : BaseExchangeDetailsViewModel {
                override val exchangeDetails: State<ExchangeDetails?> get() = mutableStateOf(value = _exchangeDetails)
                override val requestStatus: State<RequestStatusEnum> get() = mutableStateOf(value = SUCCESS)
                override val error: State<String?> get() = mutableStateOf(value = null)

                override fun getExchangeDetails(id: String) {}
            },
            object : BaseExchangeDetailsViewModel {
                override val exchangeDetails: State<ExchangeDetails?> get() = mutableStateOf(value = null)
                override val requestStatus: State<RequestStatusEnum> get() = mutableStateOf(value = LOADING)
                override val error: State<String?> get() = mutableStateOf(value = null)

                override fun getExchangeDetails(id: String) {}
            },
            object : BaseExchangeDetailsViewModel {
                override val exchangeDetails: State<ExchangeDetails?> get() = mutableStateOf(value = null)
                override val requestStatus: State<RequestStatusEnum> get() = mutableStateOf(value = ERROR)
                override val error: State<String?> get() = mutableStateOf(value = null)

                override fun getExchangeDetails(id: String) {}
            },
        )
}
