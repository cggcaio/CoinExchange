package com.cggcaio.coinexchange.exchange.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cggcaio.coinexchange.core.base.BaseUseCase
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.EMPTY_LIST
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.ERROR
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.LOADING
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum.SUCCESS
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.domain.usecase.GetExchangesUseCase
import com.cggcaio.coinexchange.network.error.model.CustomNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ExchangeListViewModel
    @Inject
    constructor(
        private val _getExchangesUseCase: GetExchangesUseCase,
    ) : ViewModel(),
        BaseExchangeListViewModel {
        private val _exchanges = mutableStateOf<List<Exchange>?>(null)

        private val _visibleExchanges = mutableStateOf<List<Exchange>?>(null)
        override val visibleExchanges: State<List<Exchange>?> get() = _visibleExchanges

        private val _listStatus = mutableStateOf(ExchangeListStatusEnum.NONE)
        override val listStatus: State<ExchangeListStatusEnum> get() = _listStatus

        private val _error = mutableStateOf<String?>(null)
        override val error: State<String?> get() = _error

        override fun getExchanges() {
            _listStatus.value = LOADING
            _getExchangesUseCase
                .produce(params = BaseUseCase.None)
                .map { exchanges ->
                    if (exchanges.isNullOrEmpty()) {
                        _listStatus.value = EMPTY_LIST
                    } else {
                        _exchanges.value = exchanges
                        _visibleExchanges.value = exchanges
                        _listStatus.value = SUCCESS
                    }
                }.catch { exc ->
                    if (exc is CustomNetworkException) {
                        _error.value = exc.friendlyMessage
                    }
                    _listStatus.value = ERROR
                }.launchIn(viewModelScope)
        }

        override fun filterExchanges(query: String) {
            when (query) {
                "" -> {
                    _visibleExchanges.value = _exchanges.value
                    _listStatus.value = SUCCESS
                }

                else -> {
                    _visibleExchanges.value =
                        _exchanges.value?.filter { exchange ->
                            exchange.name.contains(query, ignoreCase = true)
                        }
                    if (_visibleExchanges.value.isNullOrEmpty()) {
                        _listStatus.value = EMPTY_LIST
                    } else {
                        _listStatus.value = SUCCESS
                    }
                }
            }
        }
    }

interface BaseExchangeListViewModel {
    val listStatus: State<ExchangeListStatusEnum>
    val visibleExchanges: State<List<Exchange>?>
    val error: State<String?>

    fun getExchanges()

    fun filterExchanges(query: String)
}
