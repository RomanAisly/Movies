package com.example.movies.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FilmsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(filmItems: List<FilmsEntity>)

    @Upsert
    suspend fun upsertFilms(item: List<FilmsEntity>)

    @Delete
    suspend fun deleteFilms(item: FilmsEntity)

    @Query("SELECT * FROM films_entity WHERE id =:id")
    suspend fun getFilmsById(id: Int): FilmsEntity
}