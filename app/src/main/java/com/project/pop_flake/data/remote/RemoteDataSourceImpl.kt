package com.project.pop_flake.data.remote

import android.util.Log
import com.project.pop_flake.model.MovieDetail
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService):RemoteDataSource {
    override suspend fun getComingSoonMovies(): List<MovieDetail>? {
        val result=retrofitService.getComingSoon("en")
        if (result.isSuccessful){
            return result.body()?.items
        }else{
            throw Exception(result.message())
        }
    }

    override suspend fun getInTheaterMovies(): List<MovieDetail>? {
        val result=retrofitService.getInTheater("en")
        if (result.isSuccessful){
            return result.body()?.items
        }else{

            throw Exception(result.message())
        }    }

    override suspend fun getTopRatedMovies(): List<MovieDetail>? {
        val result=retrofitService.getTopRated("en")
        if (result.isSuccessful){
            return result.body()?.items
        }else{
            throw Exception(result.message())
        }    }

    override suspend fun getBoxOffice(): List<MovieDetail>? {
        val result=retrofitService.getBoxOffice("en")
        if (result.isSuccessful){
            return result.body()?.items
        }else{
            throw Exception(result.message())
        }    }

    override suspend fun searchByTitle(title:String): List<MovieDetail>? {
        val result=retrofitService.searchWithTitle("en",title)
        if (result.isSuccessful){
            return result.body()?.results
        }else{
            throw Exception(result.message())
        }    }
}