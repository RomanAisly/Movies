package com.example.movies.data.remote

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): Call<List<ResponseDTO>>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/"
        const val API_KEY = "fe4a220f82927723e66d22c09c2555ba"

        fun create(): MovieApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(MovieApi::class.java)
        }
    }

}