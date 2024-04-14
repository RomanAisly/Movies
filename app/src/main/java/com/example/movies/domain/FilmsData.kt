package com.example.movies.domain

import com.example.movies.data.remote.ResultDTO

data class FilmsData(
    val page: Int,
    val results: List<ResultDTO>,
    val totalPages: Int,
    val totalResults: Int
)
