package com.example.crypttestproject.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CryptoApi {

    @GET
    suspend fun getAllCoins(@Url url: String): ArrayList<Coin>

    @GET
    suspend fun getTransactions(@Url url: String): ArrayList<Transaction>
}