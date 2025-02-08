package com.cggcaio.coinexchange.exchange.presentation.screen

import android.content.res.Configuration
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
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cggcaio.coinexchange.core.components.CustomShimmer
import com.cggcaio.coinexchange.core.components.CustomToolbar
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.ERROR
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.LOADING
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.NONE
import com.cggcaio.coinexchange.core.constants.RequestStatusEnum.SUCCESS
import com.cggcaio.coinexchange.core.utils.noRippleClickable
import com.cggcaio.coinexchange.core.widgets.ErrorView
import com.cggcaio.coinexchange.exchange.domain.model.ExchangeDetails
import com.cggcaio.coinexchange.exchange.presentation.preview.ExchangeDetailsViewModelPreview
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.BaseExchangeDetailsViewModel
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.ExchangeDetailsViewModel
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography
import com.cggcaio.coinexchange.ui.theme.CoinExchangeTheme

@Composable
fun ExchangeDetailsScreen(
    exchangeId: String,
    detailsViewModel: BaseExchangeDetailsViewModel = hiltViewModel<ExchangeDetailsViewModel>(),
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

        when (detailsViewModel.requestStatus.value) {
            ERROR ->
                ErrorView(
                    message = detailsViewModel.error.value,
                    retry = { detailsViewModel.getExchangeDetails(id = exchangeId) },
                )

            SUCCESS -> SuccessDetailsExchangeView(details = details.value)

            NONE, LOADING -> LoadingDetailsView()
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
    loading: Boolean = false,
    isLink: Boolean = false,
    uriHandler: UriHandler = LocalUriHandler.current,
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
            Row(
                modifier =
                    Modifier.noRippleClickable {
                        if (isLink) {
                            uriHandler.openUri(info)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = info, style = typography.bodyBold)
                if (isLink) {
                    Spacer(modifier = Modifier.size(size = 8.dp))
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = color.secondary,
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingDetailsView() {
    CustomShimmer { brush ->
        Text(
            modifier = Modifier.background(brush = brush, shape = RoundedCornerShape(10.dp)),
            text = "Binance",
            style = typography.header3.copy(color = Color.Transparent),
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        Text(
            modifier = Modifier.background(brush = brush, shape = RoundedCornerShape(10.dp)),
            text = "ID: BINANCE",
            style = typography.body.copy(color = Color.Transparent),
        )
        Spacer(modifier = Modifier.size(size = 16.dp))
        SectionTitle(title = "Informações gerais")
        InfoItem(title = "Volume 1h ($)", info = "", loading = true)
        InfoItem(title = "Volume 1 dia ($)", info = "", loading = true)
        InfoItem(title = "Volume 1 mês ($)", info = "", loading = true)
        InfoItem(title = "Website", info = "", loading = true)
        Spacer(modifier = Modifier.size(size = 16.dp))
        SectionTitle(title = "Datas")
        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Cotações",
                info = "",
                loading = true,
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Cotações",
                info = "",
                loading = true,
            )
        }

        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Ordens",
                info = "",
                loading = true,
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Ordens",
                info = "",
                loading = true,
            )
        }

        Row {
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Início das Trocas",
                info = "",
                loading = true,
            )
            InfoItem(
                modifier = Modifier.weight(1f),
                title = "Fim das Trocas",
                info = "",
                loading = true,
            )
        }
    }
}

@Composable
private fun SuccessDetailsExchangeView(details: ExchangeDetails?) {
    Text(
        text = details?.name ?: "",
        style = typography.header3,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.size(size = 4.dp))
    Text(
        text = "ID: ${details?.exchangeId}",
        style = typography.body.copy(color = color.textMuted),
    )
    Spacer(modifier = Modifier.size(size = 16.dp))
    SectionTitle(title = "Informações gerais")
    InfoItem(title = "Volume 1h ($)", info = details?.volume1HrsUsd ?: "")
    InfoItem(title = "Volume 1 dia ($)", info = details?.volume1DayUsd ?: "")
    InfoItem(title = "Volume 1 mês ($)", info = details?.volume1MthUsd ?: "")
    InfoItem(title = "Website", info = details?.website ?: "", isLink = true)

    Spacer(modifier = Modifier.size(size = 16.dp))
    SectionTitle(title = "Datas")
    Row {
        InfoItem(
            modifier = Modifier.weight(1f),
            title = "Início das Cotações",
            info = details?.dataQuoteStart ?: "",
        )
        InfoItem(
            modifier = Modifier.weight(1f),
            title = "Fim das Cotações",
            info = details?.dataQuoteEnd ?: "",
        )
    }

    Row {
        InfoItem(
            modifier = Modifier.weight(1f),
            title = "Início das Ordens",
            info = details?.dataOrderStart ?: "",
        )
        InfoItem(
            modifier = Modifier.weight(1f),
            title = "Fim das Ordens",
            info = details?.dataOrderEnd ?: "",
        )
    }

    Row {
        InfoItem(
            modifier = Modifier.weight(1f),
            title = "Início das Trocas",
            info = details?.dataTradeStart ?: "",
        )
        InfoItem(
            modifier = Modifier.weight(1f),
            title = "Fim das Trocas",
            info = details?.dataTradeEnd ?: "",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangeDetailsScreenPreview(
    @PreviewParameter(ExchangeDetailsViewModelPreview::class) detailsViewModel: BaseExchangeDetailsViewModel,
) {
    CoinExchangeTheme {
        ExchangeDetailsScreen(
            exchangeId = "BINANCE",
            detailsViewModel = detailsViewModel,
            goToBack = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExchangeDetailsScreenDarkPreview(
    @PreviewParameter(ExchangeDetailsViewModelPreview::class) detailsViewModel: BaseExchangeDetailsViewModel,
) {
    CoinExchangeTheme {
        ExchangeDetailsScreen(
            exchangeId = "BINANCE",
            detailsViewModel = detailsViewModel,
            goToBack = {},
        )
    }
}
