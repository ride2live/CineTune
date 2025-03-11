package com.nolwendroid.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nolwendroid.core.data.room.movies.MovieDao
import com.nolwendroid.core.data.room.movies.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class FavoritesDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}