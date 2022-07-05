package com.aplicada2.parcialapi.data.remote.repository

import com.aplicada2.parcialapi.data.remote.Api.CoinsApi
import com.aplicada2.parcialapi.data.remote.model.Crypto
import retrofit2.HttpException
import com.aplicada2.parcialapi.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.flow



class CoinsRepository @Inject constructor(
    private val api : CoinsApi
){
    fun getCoins(): Flow<Resource<List<Crypto>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = api.getCoins()
            emit(Resource.Success(coins))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postCoins(coins: Crypto): Crypto {
        return api.postCoins(coins)
    }
}