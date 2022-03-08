package com.project.pop_flake.data.repo

import androidx.lifecycle.LiveData
import com.project.pop_flake.model.MovieDetail

interface Repository {
    suspend fun getComingSoonMovies():List<MovieDetail>?
    suspend fun getInTheaterMovies():List<MovieDetail>?
    suspend fun getTopRatedMovies():List<MovieDetail>?
    suspend fun getBoxOffice():List<MovieDetail>?
    suspend fun searchByTitle(title:String):List<MovieDetail>?


     fun getComingSoonMoviesFromRoom(): LiveData<List<MovieDetail>>
     fun getInTheaterMoviesFromRoom(): LiveData<List<MovieDetail>>
     fun getTopRatedFromRoom(): LiveData<List<MovieDetail>>
     fun getBoxOfficeFromRoom(): LiveData<List<MovieDetail>>

    suspend fun insertComingSoonMoviesToRoom()
    suspend fun insertInTheaterMoviesToRoom()
    suspend fun insertTopRatedToRoom()
    suspend fun insertBoxOfficeToRoom()
}