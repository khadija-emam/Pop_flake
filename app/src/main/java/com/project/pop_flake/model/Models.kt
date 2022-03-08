package com.project.pop_flake.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="movie_table")
data class MovieDetail(
    @PrimaryKey()
    @SerializedName("id") val id : String,

    @ColumnInfo(name="title")
    @SerializedName("title") val name : String?=null,

    @ColumnInfo(name="fullTitle")
    @SerializedName("fullTitle") val fullTitle : String?=null,

    @ColumnInfo(name="image")
    @SerializedName("image") val image : String?=null,

    @ColumnInfo(name="plot")
    @SerializedName("plot")val plot:String?=null,

    @ColumnInfo(name="status")
    @SerializedName("status") var status:String?=null

):Parcelable

data class MoviesResponse(
    @SerializedName("items")val items:List<MovieDetail>
)
data class SearchResponse (

    @SerializedName("searchType") val searchType : String,
    @SerializedName("expression") val expression : String,
    @SerializedName("results") val results : List<MovieDetail>,
    @SerializedName("errorMessage") val errorMessage : String
)
