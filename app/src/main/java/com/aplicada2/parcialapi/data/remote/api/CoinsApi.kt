package com.aplicada2.parcialapi.data.remote.Api

import com.aplicada2.parcialapi.data.remote.model.Crypto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoinsApi {
    @GET("api/Crypto")
    suspend fun getCoins(): List<Crypto>

    @POST("api/Crypto")
    suspend fun postCoins(@Body coins: Crypto): Crypto
}