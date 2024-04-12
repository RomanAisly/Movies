package com.example.movies.data.remote.respond

data class MovieListDTO(
    val page: Int,
    val results: List<ResultDTO>,
    val totalPages: Int,
    val totalResults: Int
)