package com.example.movies.data.remote

import kotlinx.coroutines.flow.Flow

interface FilmsRepository {
    suspend fun getFilmsByApi(): Flow<CheckConnection<List<ResultDTO>>>
}