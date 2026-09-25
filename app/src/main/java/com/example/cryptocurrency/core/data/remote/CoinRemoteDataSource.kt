package com.example.cryptocurrency.core.data.remote

import com.example.cryptocurrency.core.data.networking.safeGet
import com.example.cryptocurrency.core.data.remote.dto.CoinDto
import com.example.cryptocurrency.core.data.remote.dto.CoinDetailDto // تأكد من استدعاء هذا الـ DTO
import com.example.cryptocurrency.core.domain.util.DataError
import com.example.cryptocurrency.core.domain.util.Result
import io.ktor.client.HttpClient

class CoinRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getCoins(): Result<List<CoinDto>, DataError.Network> {
        return safeGet(
            client = httpClient,
            url = "https://api.coinpaprika.com/v1/coins"
        )
    }
    suspend fun getCoinById(coinId: String): Result<CoinDetailDto, DataError.Network> {
        return safeGet(
            client = httpClient,
            url = "https://api.coinpaprika.com/v1/coins/$coinId"
        )
    }

}