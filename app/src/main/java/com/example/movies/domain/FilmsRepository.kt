package com.example.movies.domain

import com.example.movies.data.remote.CheckConnection
import com.example.movies.data.remote.ResultDTO
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {
    suspend fun getFilmsRemote(): Flow<CheckConnection<List<ResultDTO>>>
}