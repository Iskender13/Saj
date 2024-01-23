package com.example.saj.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.saj.data.Character
import com.example.saj.data.Repository
import com.example.saj.data.Resource


class MainViewModel  (private val repository: Repository):ViewModel(){
    fun getCharacters(): LiveData<Resource<List<Character>>> = repository.getCharacters()
}