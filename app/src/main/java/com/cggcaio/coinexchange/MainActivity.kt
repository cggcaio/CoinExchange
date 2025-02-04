package com.cggcaio.coinexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cggcaio.coinexchange.exchange.navigation.ExchangeGraph
import com.cggcaio.coinexchange.exchange.navigation.exchangeGraph
import com.cggcaio.coinexchange.ui.theme.AppTheme.color
import com.cggcaio.coinexchange.ui.theme.CoinExchangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinExchangeTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = color.background,
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                        navController = navController,
                        startDestination = ExchangeGraph,
                    ) {
                        exchangeGraph(navController = navController)
                    }
                }
            }
        }
    }
}
