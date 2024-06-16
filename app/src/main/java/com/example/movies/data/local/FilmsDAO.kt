package com.example.movies.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FilmsDAO {

    @Upsert
    suspend fun upsertFilms(item: List<FilmsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(item: List<FilmsEntity>)

    @Delete
    suspend fun deleteFilms(item: FilmsEntity)

    @Query("SELECT * FROM FilmsEntity WHERE id =:id")
    suspend fun getFilmsById(id: Int): FilmsEntity
}