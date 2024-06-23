package com.example.movies.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.movies.domain.FilmItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDAO {

    @Upsert
    suspend fun upsertFilms(filmItems: List<FilmsEntity>)


    @Query("SELECT * FROM films_entity")
    fun getCachedFilms(): Flow<List<FilmItem>>

//    @Query("SELECT * FROM films_entity WHERE id =:id")
//    suspend fun getFilmsById(id: Int): FilmsEntity
}