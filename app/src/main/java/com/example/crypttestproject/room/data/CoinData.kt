package com.example.crypttestproject.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.crypttestproject.data.Coin

@Entity(tableName = "coinData")
data class CoinData(
    @ColumnInfo(name = "coin_name") val coin: Coin
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}