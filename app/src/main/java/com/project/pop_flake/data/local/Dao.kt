package com.project.pop_flake.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.pop_flake.model.MovieDetail

@Dao
interface Dao {
    @Query("select * from movie_table where status= 'comingSoon'")
    fun getComingSoonMoviesFromRoom(): LiveData<List<MovieDetail>>

    @Query("select * from movie_table where status= 'inTheater' ")
    fun getInTheaterMoviesFromRoom(): LiveData<List<MovieDetail>>

    @Query("select * from movie_table where status= 'topRated' ")
    fun getTopRatedMoviesFromRoom(): LiveData<List<MovieDetail>>

    @Query("select * from movie_table where status= 'boxOffice' ")
    fun getBoxOfficeFromRoom(): LiveData<List<MovieDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInTheaterMoviesToRoom(Movies:List<MovieDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComingSoonMoviesToRoom(Movies:List<MovieDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMoviesToRoom(Movies:List<MovieDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoxOfficeToRoom(Movies:List<MovieDetail>)
   }