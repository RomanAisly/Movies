package com.example.movies.domain

data class FilmsResults(
    val backdropPath: String,
    val genreIds: List<Int>,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Double
)
