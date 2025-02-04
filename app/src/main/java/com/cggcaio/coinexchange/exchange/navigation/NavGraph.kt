package com.cggcaio.coinexchange.exchange.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.cggcaio.coinexchange.exchange.presentation.screen.ExchangeCurrentRateScreen
import com.cggcaio.coinexchange.exchange.presentation.screen.ExchangeListScreen

fun NavGraphBuilder.exchangeGraph(navController: NavController) {
    navigation<ExchangeGraph>(startDestination = ExchangeList) {
        composable<ExchangeList> {
            ExchangeListScreen(
                goToDetailScreen = { id ->
                    navController.navigate(route = ExchangeCurrentRate(id = id))
                },
            )
        }
        composable<ExchangeCurrentRate> { backStackEntry ->
            val exchange: ExchangeCurrentRate = backStackEntry.toRoute()
            ExchangeCurrentRateScreen(
                exchangeId = exchange.id,
                goToBack = { navController.popBackStack() },
            )
        }
    }
}
