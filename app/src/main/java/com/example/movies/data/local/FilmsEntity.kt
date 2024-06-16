package com.example.movies.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmsEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
