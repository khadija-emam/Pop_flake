package com.project.pop_flake.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.project.pop_flake.data.local.MovieDatabase
import com.project.pop_flake.data.remote.RemoteDataSource
import com.project.pop_flake.model.MovieDetail
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource
                                         ,private val database: MovieDatabase) :Repository {

    override suspend fun getComingSoonMovies(): List<MovieDetail>? {
       return remoteDataSource.getComingSoonMovies()
    }

    override suspend fun getInTheaterMovies(): List<MovieDetail>? {
       return remoteDataSource.getInTheaterMovies()
    }

    override suspend fun getTopRatedMovies(): List<MovieDetail>? {
       return remoteDataSource.getTopRatedMovies()
    }

    override suspend fun getBoxOffice(): List<MovieDetail>? {
        return remoteDataSource.getBoxOffice()
    }

    override suspend fun searchByTitle(title: String): List<MovieDetail>? {
        return remoteDataSource.searchByTitle(title)
    }

    override  fun getComingSoonMoviesFromRoom(): LiveData<List<MovieDetail>> {

        return database.moviesDatabaseDao.getComingSoonMoviesFromRoom()
    }

    override  fun getInTheaterMoviesFromRoom(): LiveData<List<MovieDetail>> {
        return database.moviesDatabaseDao.getInTheaterMoviesFromRoom()
    }

    override  fun getTopRatedFromRoom(): LiveData<List<MovieDetail>> {
        return database.moviesDatabaseDao.getTopRatedMoviesFromRoom()
    }

    override  fun getBoxOfficeFromRoom(): LiveData<List<MovieDetail>> {
        return database.moviesDatabaseDao.getBoxOfficeFromRoom()
    }

    override suspend fun insertComingSoonMoviesToRoom() {
         val movies=getComingSoonMovies()?.toMutableList()
        if (movies!=null) {
            movies.stream().forEach { obj -> obj.status = "comingSoon" }

            return database.moviesDatabaseDao.insertComingSoonMoviesToRoom(movies)

        }
    }

    override suspend fun insertInTheaterMoviesToRoom() {
        val movies= getComingSoonMovies()?.toMutableList()
        if (movies!=null){
        movies.stream().forEach { obj -> obj.status="inTheater" }
        return database.moviesDatabaseDao.insertInTheaterMoviesToRoom(movies)
    }
    }

    override suspend fun insertTopRatedToRoom() {
        val movies=getTopRatedMovies()?.toMutableList()
        if (movies!=null){
        movies.stream().forEach { obj -> obj.status="topRated" }
        return database.moviesDatabaseDao.insertTopRatedMoviesToRoom(movies)
    }
    }

    override suspend fun insertBoxOfficeToRoom() {
        val movies=getBoxOffice()?.toMutableList()
        if (movies!=null){
            movies.stream().forEach { obj -> obj.status="boxOffice" }
        return database.moviesDatabaseDao.insertBoxOfficeToRoom(movies)
    }
    }

}