package com.example.crypttestproject.useCase

import com.example.crypttestproject.data.Coin
import com.example.crypttestproject.room.database.AppDatabase

class MainUseCase(private val database: AppDatabase) {

    fun getAllCoins(): List<Coin> {
        return database.cainDataDao().getAll().map { it.coin }
    }
}