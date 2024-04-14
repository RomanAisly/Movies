package com.example.movies.domain.network

import com.example.movies.data.remote.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit =
    Retrofit.Builder().baseUrl(MovieApi.BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .build()