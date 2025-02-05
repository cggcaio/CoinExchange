package com.cggcaio.coinexchange.exchange.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cggcaio.coinexchange.core.components.CustomToolbar
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.ExchangeDetailsViewModel
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography

@Composable
fun ExchangeCurrentRateScreen(
    exchangeId: String,
    detailsViewModel: ExchangeDetailsViewModel = hiltViewModel(),
    goToBack: () -> Unit,
) {
    val details = remember { detailsViewModel.exchangeDetails }

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
            text = details.value?.name ?: "",
            style = typography.header3,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        Text(
            text = "ID: ${details.value?.exchangeId}",
            style = typography.body.copy(color = color.textMuted),
        )

        Spacer(modifier = Modifier.size(size = 16.dp))
        SectionTitle(title = "Informações gerais")
        InfoItem(title = "Volume 1h ($)", info = details.value?.volume1HrsUsd ?: "")
        InfoItem(title = "Volume 1 dia ($)", info = details.value?.volume1DayUsd ?: "")
        InfoItem(title = "Volume 1 mês ($)", info = details.value?.volume1MthUsd ?: "")
        InfoItem(title = "Website", info = details.value?.website ?: "")

        Spacer(modifier = Modifier.size(size = 16.dp))
        SectionTitle(title = "Datas")
        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Cotações",
                info = details.value?.dataQuoteStart ?: "",
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Cotações",
                info = details.value?.dataQuoteEnd ?: "",
            )
        }

        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Ordens",
                info = details.value?.dataOrderStart ?: "",
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Ordens",
                info = details.value?.dataOrderEnd ?: "",
            )
        }

        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Trocas",
                info = details.value?.dataTradeStart ?: "",
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Trocas",
                info = details.value?.dataTradeEnd ?: "",
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = typography.header4.copy(color = color.secondary),
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp),
    )
}

@Composable
private fun InfoItem(
    modifier: Modifier = Modifier,
    title: String,
    info: String,
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        Text(
            text = title,
            style = typography.bodySmall.copy(color = color.textMuted),
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        Text(text = info, style = typography.bodyBold)
    }
}
