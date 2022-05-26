package com.example.crypttestproject.modules

import com.example.crypttestproject.useCase.AddCryptoCurrencyUseCase
import com.example.crypttestproject.useCase.MainUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object UseCasesModule {
    fun get(): Module {
        return module {
            single { MainUseCase(database = get()) }
            single { AddCryptoCurrencyUseCase(coinDataDao = get()) }
        }
    }
}