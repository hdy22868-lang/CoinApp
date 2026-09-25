package com.example.cryptocurrency.core.presentation.coin_list.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.core.domain.use_cases.GetCoinsUseCase
import com.example.cryptocurrency.core.domain.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinsUseCase: GetCoinsUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state.asStateFlow()

    init {
        getCoins()
    }

    fun getCoins(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            when(val result = coinsUseCase()){
                is Result.Success ->{
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coinsList = result.data
                        )
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