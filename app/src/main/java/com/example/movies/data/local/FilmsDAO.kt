package com.example.movies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.domain.FilmItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(filmItems: List<FilmsEntity>)

    @Query("SELECT * FROM films_entity")
    fun getCachedFilms(): Flow<List<FilmItem>>

//    @Query("SELECT * FROM films_entity WHERE id =:id")
//    suspend fun getFilmsById(id: Int): FilmsEntity
}