package com.example.movies.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {

    @GET("3/movie/popular")
    suspend fun getFilmsByApi(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<List<ResponseDTO>>
}