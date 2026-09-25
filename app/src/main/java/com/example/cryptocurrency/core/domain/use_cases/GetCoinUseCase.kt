package com.example.cryptocurrency.core.domain.use_cases

import com.example.cryptocurrency.core.domain.model.CoinDetail
import com.example.cryptocurrency.core.domain.repository.CoinRepository
import com.example.cryptocurrency.core.domain.util.DataError
import com.example.cryptocurrency.core.domain.util.Result

class GetCoinUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String): Result<CoinDetail, DataError.Network> {
        return repository.getCoinById(coinId)
    }
}