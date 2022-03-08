package com.project.pop_flake.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.pop_flake.model.MovieDetail

@Database(entities = [MovieDetail::class],version=1,exportSchema = false)
abstract class MovieDatabase :RoomDatabase(){
    abstract val moviesDatabaseDao: Dao
}

