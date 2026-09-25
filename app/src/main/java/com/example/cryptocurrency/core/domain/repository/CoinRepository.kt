package com.example.cryptocurrency.core.domain.repository

import com.example.cryptocurrency.core.domain.util.Result
import com.example.cryptocurrency.core.domain.model.Coin
import com.example.cryptocurrency.core.domain.model.CoinDetail
import com.example.cryptocurrency.core.domain.util.DataError


interface CoinRepository{
    suspend fun getCoins(): Result<List<Coin>, DataError.Network>

    suspend fun getCoinById(coinId: String): Result<CoinDetail, DataError.Network>
}