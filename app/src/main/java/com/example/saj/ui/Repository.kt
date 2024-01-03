package com.example.saj.ui


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.saj.data.AppApiService
import com.example.saj.data.RikModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val apiService: AppApiService) {


    fun getCharacters():MutableLiveData<RikModel> {
        val rik = MutableLiveData<RikModel>()
        apiService.getCharacters().enqueue(object : Callback<RikModel> {
            override fun onResponse(call: Call<RikModel>, response: Response<RikModel>) {
                if (response.isSuccessful && response.body()!=null){
                    rik.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<RikModel>, t: Throwable) {
                Log.e("ololo", "onFailure: ${t.localizedMessage}" )
            }
            
        })
        return rik
    }
}