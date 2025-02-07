package com.cggcaio.coinexchange.exchange.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cggcaio.coinexchange.core.components.CustomShimmer
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
    val loading = remember { detailsViewModel.loading }

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
        InfoItem(title = "Volume 1h ($)", info = details.value?.volume1HrsUsd ?: "", loading = loading.value)
        InfoItem(title = "Volume 1 dia ($)", info = details.value?.volume1DayUsd ?: "", loading = loading.value)
        InfoItem(title = "Volume 1 mês ($)", info = details.value?.volume1MthUsd ?: "", loading = loading.value)
        InfoItem(title = "Website", info = details.value?.website ?: "", loading = loading.value)

        Spacer(modifier = Modifier.size(size = 16.dp))
        SectionTitle(title = "Datas")
        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Cotações",
                info = details.value?.dataQuoteStart ?: "",
                loading = loading.value,
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Cotações",
                info = details.value?.dataQuoteEnd ?: "",
                loading = loading.value,
            )
        }

        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Ordens",
                info = details.value?.dataOrderStart ?: "",
                loading = loading.value,
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Ordens",
                info = details.value?.dataOrderEnd ?: "",
                loading = loading.value,
            )
        }

        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Trocas",
                info = details.value?.dataTradeStart ?: "",
                loading = loading.value,
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Trocas",
                info = details.value?.dataTradeEnd ?: "",
                loading = loading.value,
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
    loading: Boolean,
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        Text(
            text = title,
            style = typography.bodySmall.copy(color = color.textMuted),
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        if (loading) {
            CustomShimmer { brush ->
                Text(
                    modifier = Modifier.background(brush = brush, shape = RoundedCornerShape(10.dp)),
                    text = "31/07/2025",
                    style = typography.bodyBold.copy(color = Color.Transparent),
                )
            }
        } else {
            Text(text = info, style = typography.bodyBold)
        }
    }
}
