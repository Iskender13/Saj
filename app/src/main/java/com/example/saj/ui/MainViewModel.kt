package com.example.saj.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saj.data.AppApiService
import com.example.saj.data.RikModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val getCharacters: MutableLiveData<RikModel> by lazy {
        repository.getCharacters()
    }
}