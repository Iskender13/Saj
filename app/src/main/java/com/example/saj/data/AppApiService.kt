package com.example.saj.data

import retrofit2.Call
import retrofit2.http.GET

interface AppApiService {
    @GET("character")
    fun getCharacters():Call<RikModel>
}