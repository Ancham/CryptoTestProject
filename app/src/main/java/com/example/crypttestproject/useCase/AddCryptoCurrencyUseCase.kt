package com.example.crypttestproject.useCase

import com.example.crypttestproject.data.Coin
import com.example.crypttestproject.data.CryptoApiImpl
import com.example.crypttestproject.room.daos.CoinDataDao
import com.example.crypttestproject.room.data.CoinData

class AddCryptoCurrencyUseCase(private val coinDataDao: CoinDataDao) {

    private val cryptoApiImpl by lazy { CryptoApiImpl() }

    fun addCoin(coin: Coin) {
        coinDataDao.insertCoin(CoinData(coin))
    }

    suspend fun getAllCoinsFormApi(): ArrayList<Coin> {
        return cryptoApiImpl.getAllCoins()
    }
}