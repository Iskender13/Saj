package com.example.saj.data

import androidx.lifecycle.LiveData
import com.example.saj.ui.Base.BaseRepository

class Repository (private val api: AppApiService):BaseRepository(api) {

    fun getCharacters():LiveData<Resource<List<Character>>> = performRequest {
        api.getCharacters().body()?.results ?: emptyList()
    }
    fun getCharacterDetails(id: Int): LiveData<Resource<Character>> = performRequest{
        api.getCharacterDetails(id).body()!!
    }

}