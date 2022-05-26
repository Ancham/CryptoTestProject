package com.example.crypttestproject.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.crypttestproject.room.converters.Converters
import com.example.crypttestproject.room.daos.CoinDataDao
import com.example.crypttestproject.room.data.CoinData

@Database(entities = [CoinData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cainDataDao(): CoinDataDao
}
