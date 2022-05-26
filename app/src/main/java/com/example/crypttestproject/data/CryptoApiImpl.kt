package com.example.crypttestproject.data

import com.example.crypttestproject.retrofit.RetrofitInstance
import retrofit2.Response

class CryptoApiImpl {
    private val remoteCryptoApi by lazy { RetrofitInstance.remoteRetrofitInstance!!.create(CryptoApi::class.java) }
    private val localCryptoApi by lazy { RetrofitInstance.localRetrofitInstance!!.create(CryptoApi::class.java) }


    suspend fun getAllCoins(): ArrayList<Coin> {
        return remoteCryptoApi.getAllCoins("coins/list")
    }

    suspend fun getAllTransactions(): ArrayList<Transaction> {
        return localCryptoApi.getTransactions("transactions")
    }
}