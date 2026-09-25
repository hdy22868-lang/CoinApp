package com.example.cryptocurrency.core.presentation

import kotlinx.serialization.Serializable

@Serializable
object CoinListRoute

@Serializable
data class CoinDetailRoute(val coinId: String)