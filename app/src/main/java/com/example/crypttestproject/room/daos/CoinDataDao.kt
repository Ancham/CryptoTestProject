package com.example.crypttestproject.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.crypttestproject.room.data.CoinData

@Dao
interface CoinDataDao {
    @Query("SELECT * FROM coinData")
    fun getAll(): List<CoinData>

    @Insert
    fun insertCoin(card: CoinData)
}