package com.example.movies.domain.network

import com.example.movies.data.remote.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}