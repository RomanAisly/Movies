package com.example.movies.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getFilmsByApi(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): ResponseDTO

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "fe4a220f82927723e66d22c09c2555ba"
    }

}