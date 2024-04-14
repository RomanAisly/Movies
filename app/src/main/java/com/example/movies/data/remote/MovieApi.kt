package com.example.movies.data.remote

import com.example.movies.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getFilmsData(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY,

        @Query("page") page: Int,
    ): MovieListDTO

    @GET("3/search/movie")
    suspend fun getFilmsFromSearch(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY,

        @Query("page") page: Int,
    ): MovieListDTO

}