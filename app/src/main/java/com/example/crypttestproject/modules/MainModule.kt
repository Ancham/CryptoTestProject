package com.example.crypttestproject.modules

import android.app.Application
import androidx.room.Room
import com.example.crypttestproject.room.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

object MainModule {
    fun get(application: Application): Module {
        return module {
            single {
                Room.databaseBuilder(application, AppDatabase::class.java, "database-name")
                    .allowMainThreadQueries()
                    .build()
            }
            single { get<AppDatabase>().cainDataDao() }
        }
    }
}

