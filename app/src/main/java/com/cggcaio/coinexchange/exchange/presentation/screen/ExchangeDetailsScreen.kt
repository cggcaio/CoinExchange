package com.cggcaio.coinexchange.exchange.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cggcaio.coinexchange.core.components.CustomToolbar
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.ExchangeDetailsViewModel
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography

@Composable
fun ExchangeCurrentRateScreen(
    exchangeId: String,
    detailsViewModel: ExchangeDetailsViewModel = hiltViewModel(),
    goToBack: () -> Unit,
) {
    val details = remember { detailsViewModel.exchangeDetails.value }

    LaunchedEffect(true) {
        detailsViewModel.getExchangeDetails(id = exchangeId)
    }

    BackHandler {
        goToBack()
    }

    Column(modifier = Modifier.padding(all = 16.dp)) {
        CustomToolbar(
            title = "Detalhes da corretora",
            startIcon = Icons.Default.ChevronLeft,
            onClickStartIcon = { goToBack() },
        )
        Spacer(modifier = Modifier.size(size = 16.dp))

        Text(
            text = details?.name ?: "",
            style = typography.body,
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        Text(
            text = "ID: ${details?.exchangeId}",
            style = typography.body,
        )
        Spacer(modifier = Modifier.size(size = 16.dp))
    }
}
