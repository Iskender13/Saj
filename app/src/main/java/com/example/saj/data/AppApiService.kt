package com.example.saj.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApiService {
    @GET("character")
    fun getCharacters():Call<BaseResponse<Character>>

    @GET("character/{id}")
    fun getCharacterDetails(
        @Path("id") id:Int
    ):Call<Character>
}