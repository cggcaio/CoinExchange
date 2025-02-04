package com.cggcaio.coinexchange.exchange.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.coinexchange.exchange.domain.usecase.GetExchangeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@HiltViewModel
class ExchangeDetailsViewModel
    @Inject
    constructor(
        private val _getExchangeDetailsUseCase: GetExchangeDetailsUseCase,
    ) : ViewModel() {
        private val _exchangeDetails = mutableStateOf<ExchangeDetails?>(null)
        val exchangeDetails: MutableState<ExchangeDetails?> get() = _exchangeDetails

        private val _loading = mutableStateOf(false)
        val loading: State<Boolean> get() = _loading

        fun getExchangeDetails(id: String) {
            _loading.value = true
            _getExchangeDetailsUseCase
                .produce(params = GetExchangeDetailsUseCase.Params(id = id))
                .map { details ->
                    _exchangeDetails.value = details
                }.onCompletion {
                    _loading.value = false
                }.catch { exc ->
                    val a = exc
                }.launchIn(viewModelScope)
        }
    }
