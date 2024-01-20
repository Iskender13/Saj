package com.example.saj.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: AppApiService) {

    fun getCharacters(): MutableLiveData<List<Character>> {
        val rik = MutableLiveData<List<Character>>()

        api.getCharacters().enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(call: Call<BaseResponse<Character>>, response: Response<BaseResponse<Character>>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        rik.postValue(it.results)
                    }
                }
                Log.d("onResponse", "данные пришли")
            }

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                Log.e("onFailure", "данные не пришли")
            }
        })
        return rik
    }

    fun getCharacterDetails(id:Int): MutableLiveData<Character> {
        val rik = MutableLiveData<Character>()

        api.getCharacterDetails(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        rik.postValue(it)
                    }
                }
                Log.d("onResponseDetail nActivity", "данные на второй активити не пришли")
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d("ERROR", "данные не пришли")
            }
        })
        return rik
    }

}