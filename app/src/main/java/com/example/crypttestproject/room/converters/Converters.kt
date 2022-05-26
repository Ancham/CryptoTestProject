package com.example.crypttestproject.room.converters

import androidx.room.TypeConverter
import com.example.crypttestproject.data.Coin
import com.google.gson.Gson

class Converters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromCoinToString(coin: Coin): String {
        return gson.toJson(coin)
    }

    @TypeConverter
    fun toCoinFromString(coin: String): Coin {
        val fromJson = gson.fromJson(coin, Coin::class.java)
        return fromJson
    }
}