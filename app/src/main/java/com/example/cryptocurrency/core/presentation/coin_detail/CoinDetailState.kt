package com.example.cryptocurrency.core.presentation.coin_detail

import com.example.cryptocurrency.core.domain.model.CoinDetail
import com.example.cryptocurrency.core.domain.util.DataError

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: DataError.Network? = null
)