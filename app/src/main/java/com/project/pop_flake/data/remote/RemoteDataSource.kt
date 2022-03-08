package com.project.pop_flake.data.remote

import com.project.pop_flake.model.MovieDetail
import com.project.pop_flake.model.MoviesResponse

interface RemoteDataSource {
 suspend fun getComingSoonMovies():List<MovieDetail>?
    suspend fun getInTheaterMovies():List<MovieDetail>?
    suspend fun getTopRatedMovies():List<MovieDetail>?
    suspend fun getBoxOffice():List<MovieDetail>?

    suspend fun searchByTitle(title:String):List<MovieDetail>?
}