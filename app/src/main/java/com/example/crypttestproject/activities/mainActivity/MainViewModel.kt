package com.example.crypttestproject.activities.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crypttestproject.data.Coin
import com.example.crypttestproject.useCase.MainUseCase
import com.example.crypttestproject.viewModel.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCase: MainUseCase) : BaseViewModel() {

    private var _allCoins = MutableLiveData<List<Coin>>()
    val allCoins: LiveData<List<Coin>> = _allCoins

    init {
        initGetAllCoins()
    }

    private fun initGetAllCoins() {
        viewModelScope.launch {
            safeLaunch(exceptionSource = INITIALIZATION_GET_ALL_COINS) {
                _allCoins.value = mainUseCase.getAllCoins()
            }
        }
    }

    fun refreshAllCoins() {
        viewModelScope.launch {
            safeLaunch(exceptionSource = REFRESH_ALL_COINS) {
                _allCoins.postValue(mainUseCase.getAllCoins())
            }
        }
    }

    companion object {
        const val REFRESH_ALL_COINS = "REFRESH_ALL_COINS"
        const val INITIALIZATION_GET_ALL_COINS = "INITIALIZATION_GET_ALL_COINS"
    }
}