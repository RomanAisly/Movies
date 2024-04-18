package com.example.movies.data.remote

import retrofit2.http.GET

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getFilmsByApi(): ResponseDTO

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/"
        const val API_KEY = "fe4a220f82927723e66d22c09c2555ba"
    }

}