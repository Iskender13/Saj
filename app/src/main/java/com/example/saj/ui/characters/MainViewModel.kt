package com.example.saj.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saj.data.Character
import com.example.saj.data.Repository
import com.example.saj.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository):ViewModel(){
    fun getCharacters(): MutableLiveData<Resource<List<Character>>> = repository.getCharacters()
}