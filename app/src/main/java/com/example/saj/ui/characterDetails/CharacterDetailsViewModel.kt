package com.example.saj.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.saj.data.Character
import com.example.saj.data.Repository
import com.example.saj.data.Resource

class CharacterDetailsViewModel(
    private val repository: Repository
): ViewModel(){
    fun getCharacterDetails(sendId:Int): LiveData<Resource<Character>> =  repository.getCharacterDetails(sendId)
}