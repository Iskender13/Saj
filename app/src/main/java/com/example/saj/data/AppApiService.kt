package com.example.saj.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApiService {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponse<Character>>


    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id:Int
    ): Response<Character>
}