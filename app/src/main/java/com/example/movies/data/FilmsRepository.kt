package com.example.movies.data

import com.example.movies.data.remote.FilmsApi
import com.example.movies.domain.di.AppModule
import javax.inject.Inject

class FilmsRepository @Inject constructor(private val filmsApi: FilmsApi) {
    suspend fun getFilms() = filmsApi.getFilmsByApi(AppModule.API_KEY, 1)
}