package com.example.cryptocurrency.core.data.repository

import com.example.cryptocurrency.core.domain.util.Result
import com.example.cryptocurrency.core.domain.model.Coin
import com.example.cryptocurrency.core.domain.model.CoinDetail
import com.example.cryptocurrency.core.domain.util.DataError
import com.example.cryptocurrency.core.data.remote.CoinRemoteDataSource
import com.example.cryptocurrency.core.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.core.domain.model.toCoin
import com.example.cryptocurrency.core.domain.repository.CoinRepository

class CoinRepositoryImpl (
    private val remoteDataSource: CoinRemoteDataSource
): CoinRepository {

    override suspend fun getCoins(): Result<List<Coin> , DataError.Network>{
        return when (val result = remoteDataSource.getCoins()) {
            is Result.Success -> {
                val cleanCoins = result.data.map { it.toCoin() }
                Result.Success(cleanCoins)
            }
            is Result.Error -> {
                Result.Error(result.error)
            }
        }
    }
    override suspend fun getCoinById(coinId: String): Result<CoinDetail, DataError.Network> {
        return when (val result = remoteDataSource.getCoinById(coinId)) {
            is Result.Success -> {
                val cleanCoinDetail = result.data.toCoinDetail()
                Result.Success(cleanCoinDetail)
            }
            is Result.Error -> {
                println("API ERROR DETAILS: ${result.error}")
                Result.Error(result.error)
            }
        }
    }

}