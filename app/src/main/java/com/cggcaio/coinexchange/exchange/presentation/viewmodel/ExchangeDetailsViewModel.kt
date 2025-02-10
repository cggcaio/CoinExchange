package com.cggcaio.coinexchange.exchange.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.ERROR
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.LOADING
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.NONE
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.SUCCESS
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.coinexchange.exchange.domain.usecase.GetExchangeDetailsUseCase
import com.cggcaio.network.error.model.CustomNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ExchangeDetailsViewModel
    @Inject
    constructor(
        private val _getExchangeDetailsUseCase: GetExchangeDetailsUseCase,
    ) : ViewModel(),
        BaseExchangeDetailsViewModel {
        private val _exchangeDetails = mutableStateOf<ExchangeDetails?>(value = null)
        override val exchangeDetails: MutableState<ExchangeDetails?> get() = _exchangeDetails

        private val _error = mutableStateOf<String?>(value = null)
        override val error: State<String?> get() = _error

        private val _requestStatus = mutableStateOf(NONE)
        override val requestStatus: State<RequestStatusEnum> get() = _requestStatus

        override fun getExchangeDetails(id: String) {
            _requestStatus.value = LOADING
            _getExchangeDetailsUseCase
                .produce(params = GetExchangeDetailsUseCase.Params(id = id))
                .map { details ->
                    _exchangeDetails.value = details
                    _requestStatus.value = SUCCESS
                }.catch { exc ->
                    if (exc is CustomNetworkException) {
                        _error.value = exc.friendlyMessage
                    }
                    _requestStatus.value = ERROR
                }.launchIn(viewModelScope)
        }
    }

interface BaseExchangeDetailsViewModel {
    val exchangeDetails: State<ExchangeDetails?>
    val requestStatus: State<RequestStatusEnum>
    val error: State<String?>

    fun getExchangeDetails(id: String)
}
