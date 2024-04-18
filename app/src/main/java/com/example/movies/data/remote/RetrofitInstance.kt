package com.example.movies.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val okClient: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        )
        .build()

    val moviesApi: MovieApi = Retrofit.Builder()
        .baseUrl(MovieApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okClient)
        .build()
        .create(MovieApi::class.java)

}