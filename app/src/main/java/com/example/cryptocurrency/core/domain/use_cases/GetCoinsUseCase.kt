package com.example.cryptocurrency.core.domain.use_cases

import com.example.cryptocurrency.core.domain.model.Coin
import com.example.cryptocurrency.core.domain.repository.CoinRepository
import com.example.cryptocurrency.core.domain.util.DataError
import com.example.cryptocurrency.core.domain.util.Result

class GetCoinsUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(): Result<List<Coin>, DataError.Network> {
        return repository.getCoins()
    }
}