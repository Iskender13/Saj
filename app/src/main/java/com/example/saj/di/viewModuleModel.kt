package com.example.saj.di

import com.example.saj.ui.characterDetails.CharacterDetailsViewModel
import com.example.saj.ui.characters.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        MainViewModel(get())
    }

    viewModel{
        CharacterDetailsViewModel(get())
    }
}