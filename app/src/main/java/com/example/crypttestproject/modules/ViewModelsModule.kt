package com.example.crypttestproject.modules

import com.example.crypttestproject.activities.addCryptoCurrencyActivity.AddCryptoCurrencyViewModel
import com.example.crypttestproject.activities.mainActivity.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelsModule {
    fun get(): Module {
        return module {
            viewModel { AddCryptoCurrencyViewModel(addCryptoCurrencyUseCase = get()) }
            viewModel { MainViewModel(mainUseCase = get()) }
        }
    }
}

