package com.example.cryptocurrency.core.presentation.coin_list.logic

import com.example.cryptocurrency.core.domain.model.Coin
import com.example.cryptocurrency.core.domain.util.DataError

data class CoinListState(
    val isLoading: Boolean = false,
    val error: DataError.Network?=null,
    val coinsList: List<Coin> = emptyList()
)