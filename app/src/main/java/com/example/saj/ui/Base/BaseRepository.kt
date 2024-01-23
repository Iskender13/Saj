package com.example.saj.ui.Base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.saj.data.AppApiService
import com.example.saj.data.Resource
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository(private val api: AppApiService) {
    protected fun <T> performRequest(apiCall: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                emit(Resource.Success(response))
            }catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
            }
        }
}