package com.example.movies.data.local

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    fun upsetMovieList(moviesList: List<MovieEntity>)
}