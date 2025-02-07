package com.cggcaio.coinexchange.exchange.presentation.preview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.BaseExchangeListViewModel

class ExchangeListViewModelPreview : PreviewParameterProvider<BaseExchangeListViewModel> {
    private val _exchangeList =
        listOf(
            Exchange(
                id = "BINANCE",
                name = "Binance",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "KRAKEN",
                name = "Kraken",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "COINBASE",
                name = "Coinbase Pro (GDAX)",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "BITSTAMP",
                name = "Bitstamp Ltd.",
                volume1DayUsd = "$1,999,999.00",
            ),
            Exchange(
                id = "ECB",
                name = "European Central Bank",
                volume1DayUsd = "$1,999,999.00",
            ),
        )

    override val values: Sequence<BaseExchangeListViewModel> =
        sequenceOf(
            object : BaseExchangeListViewModel {
                override val listStatus: State<ExchangeListStatusEnum>
                    get() = mutableStateOf(ExchangeListStatusEnum.SUCCESS)
                override val visibleExchanges: State<List<Exchange>?>
                    get() = mutableStateOf(_exchangeList)

                override fun getExchanges() {}

                override fun filterExchanges(query: String) {}
            },
            object : BaseExchangeListViewModel {
                override val listStatus: State<ExchangeListStatusEnum>
                    get() = mutableStateOf(ExchangeListStatusEnum.EMPTY_LIST)
                override val visibleExchanges: State<List<Exchange>?>
                    get() = mutableStateOf(emptyList())

                override fun getExchanges() {}

                override fun filterExchanges(query: String) {}
            },
            object : BaseExchangeListViewModel {
                override val listStatus: State<ExchangeListStatusEnum>
                    get() = mutableStateOf(ExchangeListStatusEnum.LOADING)
                override val visibleExchanges: State<List<Exchange>?>
                    get() = mutableStateOf(emptyList())

                override fun getExchanges() {}

                override fun filterExchanges(query: String) {}
            },
            object : BaseExchangeListViewModel {
                override val listStatus: State<ExchangeListStatusEnum>
                    get() = mutableStateOf(ExchangeListStatusEnum.ERROR)
                override val visibleExchanges: State<List<Exchange>?>
                    get() = mutableStateOf(emptyList())

                override fun getExchanges() {}

                override fun filterExchanges(query: String) {}
            },
        )
}
