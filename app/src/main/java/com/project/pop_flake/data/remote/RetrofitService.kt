package com.project.pop_flake.data.remote

import com.project.pop_flake.model.MoviesResponse
import com.project.pop_flake.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("{lang}/API/ComingSoon")
    suspend fun getComingSoon(@Path("lang") lang:String): Response<MoviesResponse>

    @GET("{lang}/API/InTheaters")
    suspend fun getInTheater(@Path("lang") lang:String): Response<MoviesResponse>

    @GET("{lang}/API/Top250Movies")
    suspend fun getTopRated(@Path("lang") lang:String): Response<MoviesResponse>

    @GET("{lang}/API/BoxOffice")
    suspend fun getBoxOffice(@Path("lang") lang:String): Response<MoviesResponse>

    @GET("{lang}/API/SearchTitle/{expression}")
    suspend fun searchWithTitle(
        @Path("lang") lang:String,
        @Path("expression") expression: String
    ): Response<SearchResponse>


}