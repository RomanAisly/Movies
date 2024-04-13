package com.example.movies.data.local

import androidx.room.PrimaryKey

data class MovieEntity(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Double,

    @PrimaryKey
    val id: Int,
    val category: String
)