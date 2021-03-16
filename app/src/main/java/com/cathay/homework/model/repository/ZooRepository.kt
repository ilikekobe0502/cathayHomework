package com.cathay.homework.model.repository

import com.cathay.homework.model.api.ApiService
import com.cathay.homework.model.api.model.response.PlantItem
import com.cathay.homework.model.api.model.response.ZooItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ZooRepository constructor(private val apiService: ApiService) {

    suspend fun getZoo(): Flow<ArrayList<ZooItem>?> {
        return flow {
            val result = apiService.getZoo()
            if (!result.isSuccessful) throw HttpException(result)
            val data = result.body()?.result?.results
            emit(data)
        }
    }

    suspend fun getPlant(): Flow<ArrayList<PlantItem>?> {
        return flow {
            val result = apiService.getPlant()
            if (!result.isSuccessful) throw HttpException(result)
            val data = result.body()?.result?.results
            emit(data)
        }
    }

}