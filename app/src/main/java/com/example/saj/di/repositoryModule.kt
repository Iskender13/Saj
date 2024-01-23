package com.example.saj.di

import com.example.saj.data.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single{
        Repository(get())
    }
}