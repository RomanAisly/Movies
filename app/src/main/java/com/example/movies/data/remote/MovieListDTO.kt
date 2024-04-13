package com.example.movies.data.remote

import com.example.movies.data.remote.ResultDTO

data class MovieListDTO(
    val page: Int,
    val results: List<ResultDTO>,
    val totalPages: Int,
    val totalResults: Int
)