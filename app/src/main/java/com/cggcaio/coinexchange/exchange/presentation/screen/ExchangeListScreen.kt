package com.cggcaio.coinexchange.exchange.presentation.screen

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
import androidx.compose.material.icons.filled.FilterListOff
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cggcaio.coinexchange.core.components.CustomShimmer
import com.cggcaio.coinexchange.core.components.CustomTextField
import com.cggcaio.coinexchange.core.components.CustomToolbar
import com.cggcaio.coinexchange.core.widgets.EmptyListView
import com.cggcaio.coinexchange.core.widgets.ErrorView
import com.cggcaio.coinexchange.exchange.constants.ExchangeListStatusEnum
import com.cggcaio.coinexchange.exchange.domain.model.Exchange
import com.cggcaio.coinexchange.exchange.presentation.viewmodel.ExchangeListViewModel
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.AppTheme.typography
import com.cggcaio.coinexchange.ui.theme.CoinExchangeTheme

@Composable
fun ExchangeListScreen(
    exchangeViewModel: ExchangeListViewModel = hiltViewModel(),
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
                repeat(10) {
                    ShimmerEffect()
                    Spacer(modifier = Modifier.size(size = 8.dp))
                }
            }

            ExchangeListStatusEnum.ERROR -> {
                ErrorView {
                    exchangeViewModel.getExchanges()
                }
            }

            ExchangeListStatusEnum.EMPTY_LIST -> {
                EmptyListView(
                    title = "Nenhum resultado encontrado para sua busca",
                    body = "Verifique se você digitou o nome corretamente",
                    icon = Icons.Default.FilterListOff,
                    iconColor = color.secondary,
                )
            }

            ExchangeListStatusEnum.SUCCESS -> {
                LazyColumn(
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
    Text(text = exchange.name, style = typography.header4)
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
    CustomShimmer { brush ->
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(size = 18.dp),
                imageVector = Icons.Default.AccountBalance,
                tint = color.secondary,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(size = 8.dp))
            Text(
                modifier =
                    Modifier
                        .background(
                            brush = brush,
                            shape = RoundedCornerShape(10.dp),
                        ),
                text = "exchange.id",
                style = typography.bodySmall.copy(color = Color.Transparent),
            )
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
            Text(
                modifier =
                    Modifier
                        .background(
                            brush = brush,
                            shape = RoundedCornerShape(10.dp),
                        ),
                text = "exchange.volume1DayUsd",
                style = typography.bodySmall.copy(color = Color.Transparent),
            )
        }
        Spacer(modifier = Modifier.size(size = 4.dp))
        HorizontalDivider(thickness = 1.dp, color = color.primary200)
    }
}

// todo caioba
@Preview(showBackground = true)
@Composable
fun ExchangeListScreenPreview() {
    CoinExchangeTheme {
        ExchangeListScreen(
            goToDetailScreen = {},
        )
    }
}
