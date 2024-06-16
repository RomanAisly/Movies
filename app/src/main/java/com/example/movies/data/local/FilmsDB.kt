package com.example.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FilmsEntity::class], version = 1)
abstract class FilmsDB : RoomDatabase() {
    abstract val filmsDAO: FilmsDAO
}