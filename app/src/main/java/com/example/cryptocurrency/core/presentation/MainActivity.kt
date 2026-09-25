package com.example.cryptocurrency.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.core.presentation.coin_list.component.CoinListScreen
import com.example.cryptocurrency.core.presentation.coin_detail.component.CoinDetailScreen
import com.example.cryptocurrency.core.presentation.ui.CryptocurrencyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptocurrencyTheme(darkTheme = true) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = CoinListRoute
                ) {
                    composable<CoinListRoute> {
                        CoinListScreen(navController = navController)
                    }

                    composable<CoinDetailRoute> {
                        CoinDetailScreen()
                    }
                }
            }
        }
    }
}