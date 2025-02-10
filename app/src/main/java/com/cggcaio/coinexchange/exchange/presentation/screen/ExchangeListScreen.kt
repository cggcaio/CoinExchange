package com.cggcaio.coinexchange.exchange.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.presentation.preview.ExchangeListViewModelPreview
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.BaseExchangeListViewModel
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.ExchangeListViewModel
import com.cggcaio.core.theme.AppTheme.color
import com.cggcaio.core.theme.AppTheme.typography
import com.cggcaio.core.theme.CoinExchangeTheme
import com.cggcaio.core.components.CustomShimmer
import com.cggcaio.core.components.CustomTextField
import com.cggcaio.core.components.CustomToolbar
import com.cggcaio.core.widgets.EmptyListView
import com.cggcaio.core.widgets.ErrorView

@Composable
fun ExchangeListScreen(
    exchangeViewModel: BaseExchangeListViewModel = hiltViewModel<ExchangeListViewModel>(),
    goToDetailScreen: (String) -> Unit,
) {
    val exchangeName = remember { mutableStateOf("") }

    LaunchedEffect(true) {
        exchangeViewModel.getExchanges()
    }

    Column(modifier = Modifier.padding(all = 16.dp)) {
        CustomToolbar(title = "Corretoras")
        Spacer(modifier = Modifier.size(size = 16.dp))
        if (exchangeViewModel.listStatus.value != ExchangeListStatusEnum.ERROR) {
            CustomTextField(
                label = "Busque uma corretora",
                value = exchangeName.value,
                onValueChange = { name ->
                    exchangeName.value = name
                    exchangeViewModel.filterExchanges(query = name)
                },
            )
        }
        Spacer(modifier = Modifier.size(size = 16.dp))

        when (exchangeViewModel.listStatus.value) {
            ExchangeListStatusEnum.LOADING -> {
                ShimmerEffect()
            }

            ExchangeListStatusEnum.ERROR -> {
                ErrorView(
                    message = exchangeViewModel.error.value,
                    retry = { exchangeViewModel.getExchanges() },
                )
            }

            ExchangeListStatusEnum.EMPTY_LIST -> {
                EmptyListView(
                    title = "Nenhum resultado encontrado para sua busca",
                    body = "Verifique se você digitou o nome corretamente",
                    icon = Icons.Default.SearchOff,
                    iconColor = color.secondary,
                )
            }

            ExchangeListStatusEnum.SUCCESS -> {
                LazyColumn(
                    modifier = Modifier.testTag(tag = "ExchangeListView"),
                    verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                ) {
                    items(exchangeViewModel.visibleExchanges.value ?: emptyList()) { exchange ->
                        Column(
                            modifier =
                                Modifier.clickable {
                                    goToDetailScreen(exchange.id)
                                },
                        ) {
                            ExchangeItem(exchange)
                        }
                    }
                }
            }

            ExchangeListStatusEnum.NONE -> {}
        }
    }
}

@Composable
private fun ExchangeItem(exchange: Exchange) {
    Text(modifier = Modifier.testTag(tag = "ExchangeNameText"), text = exchange.name, style = typography.header4)
    Spacer(modifier = Modifier.size(size = 8.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(size = 18.dp),
            imageVector = Icons.Default.AccountBalance,
            tint = color.secondary,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(size = 8.dp))
        Text(text = exchange.id, style = typography.bodySmall)
    }
    Spacer(modifier = Modifier.size(size = 4.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(size = 18.dp),
            imageVector = Icons.Default.AttachMoney,
            tint = color.secondary,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(size = 8.dp))
        Text(text = exchange.volume1DayUsd.toString(), style = typography.bodySmall)
    }
    Spacer(modifier = Modifier.size(size = 4.dp))
    HorizontalDivider(thickness = 1.dp, color = color.primary200)
}

@Composable
private fun ShimmerEffect() {
    CustomShimmer(
        repeats = 10,
    ) { brush ->
        Text(
            modifier =
                Modifier
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(10.dp),
                    ),
            text = "exchange.name",
            style = typography.header4.copy(color = Color.Transparent),
        )
        Spacer(modifier = Modifier.size(size = 8.dp))
        Row(
            modifier =
                Modifier
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(10.dp),
                    ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(size = 18.dp),
                imageVector = Icons.Default.AccountBalance,
                tint = Color.Transparent,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(size = 8.dp))
            Text(
                text = "exchange.id",
                style = typography.bodySmall.copy(color = Color.Transparent),
            )
        }
        Spacer(modifier = Modifier.size(size = 4.dp))
        Row(
            modifier =
                Modifier
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(10.dp),
                    ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(size = 18.dp),
                imageVector = Icons.Default.AttachMoney,
                tint = Color.Transparent,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(size = 8.dp))
            Text(
                text = "exchange.volume1DayUsd",
                style = typography.bodySmall.copy(color = Color.Transparent),
            )
        }
        Spacer(modifier = Modifier.size(size = 4.dp))
        HorizontalDivider(thickness = 1.dp, color = color.primary200)
        Spacer(modifier = Modifier.size(size = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangeListScreenPreview(
    @PreviewParameter(ExchangeListViewModelPreview::class) exchangeViewModel: BaseExchangeListViewModel,
) {
    CoinExchangeTheme {
        ExchangeListScreen(
            exchangeViewModel = exchangeViewModel,
            goToDetailScreen = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExchangeListScreenDarkPreview(
    @PreviewParameter(ExchangeListViewModelPreview::class) exchangeViewModel: BaseExchangeListViewModel,
) {
    CoinExchangeTheme {
        ExchangeListScreen(
            exchangeViewModel = exchangeViewModel,
            goToDetailScreen = {},
        )
    }
}
