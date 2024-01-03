package com.example.saj.di

import com.example.saj.BuildConfig
import com.example.saj.data.AppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule  {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api")
            .client(okHttpClient)
            .build()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20,TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        val interceptor=HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
    @Provides
    fun provideRikApiService(
        retrofit: Retrofit
    ):AppApiService{
        return retrofit.create(AppApiService::class.java)
    }

}