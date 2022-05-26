package com.example.crypttestproject.activities.addCryptoCurrencyActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crypttestproject.data.Coin
import com.example.crypttestproject.useCase.AddCryptoCurrencyUseCase
import com.example.crypttestproject.viewModel.BaseViewModel
import kotlinx.coroutines.launch

class AddCryptoCurrencyViewModel(private val addCryptoCurrencyUseCase: AddCryptoCurrencyUseCase) :
    BaseViewModel() {

    private val _updatedCryptoList = MutableLiveData<List<Coin>>()
    val updatedCryptoList: LiveData<List<Coin>> = _updatedCryptoList

    init {
        initLoadCryptoCurrencies()
    }

    private fun initLoadCryptoCurrencies() {
        viewModelScope.launch {
            safeLaunch(exceptionSource = INITIALIZATION_LOAD_CRYPTO_CURRENCIES) {
                val data = addCryptoCurrencyUseCase.getAllCoinsFormApi()
                _updatedCryptoList.value = data
            }
        }
    }

    fun addNewCryptoToDatabase(coin: Coin?) {
        try {
            coin?.let {
                addCryptoCurrencyUseCase.addCoin(coin)
            }
                ?: kotlin.run { throw Exception("Coin cannot be empty") }

        } catch (exception: Exception) {
            Log.e("Error", "error while trying to addNewCryptoToDatabase")
            Log.e("Error", exception.stackTraceToString())

        }
    }

    companion object {
        const val INITIALIZATION_LOAD_CRYPTO_CURRENCIES = "INITIALIZATION_LOAD_CRYPTO_CURRENCIES"
    }
}