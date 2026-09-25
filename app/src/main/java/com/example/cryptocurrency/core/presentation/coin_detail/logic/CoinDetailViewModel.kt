package com.example.cryptocurrency.core.presentation.coin_detail.logic

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.cryptocurrency.core.domain.use_cases.GetCoinUseCase
import com.example.cryptocurrency.core.domain.util.Result
import com.example.cryptocurrency.core.presentation.CoinDetailRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<CoinDetailRoute>()
        getCoin(args.coinId)
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            when (val result = getCoinUseCase(coinId)) {
                is Result.Success -> {
                    _state.update {
                        it.copy(isLoading = false, coin = result.data)
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}