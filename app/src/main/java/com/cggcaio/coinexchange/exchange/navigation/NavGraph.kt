package com.cggcaio.coinexchange.exchange.navigation

import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.cggcaio.coinexchange.exchange.presentation.screen.ExchangeDetailsScreen
import com.cggcaio.coinexchange.exchange.presentation.screen.ExchangeListScreen

fun NavGraphBuilder.exchangeGraph(navController: NavController) {
    navigation<ExchangeGraph>(startDestination = ExchangeListRoute) {
        composable<ExchangeListRoute> {
            ExchangeListScreen(
                goToDetailScreen = { id ->
                    navController.navigate(route = ExchangeDetailsRoute(id = id))
                },
            )
        }
        composable<ExchangeDetailsRoute> { backStackEntry ->
            val exchange: ExchangeDetailsRoute = backStackEntry.toRoute()
            val uriHandler: UriHandler = LocalUriHandler.current

            ExchangeDetailsScreen(
                exchangeId = exchange.id,
                goToBack = { navController.popBackStack() },
                goToWeb = { uri -> uriHandler.openUri(uri) },
            )
        }
    }
}
