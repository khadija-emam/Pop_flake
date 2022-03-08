package com.project.pop_flake.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pop_flake.SingleLiveEvent
import com.project.pop_flake.data.repo.Repository
import com.project.pop_flake.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository):ViewModel(){


    private val _navigate = SingleLiveEvent<MovieDetail?>()
    val navigate: SingleLiveEvent<MovieDetail?>
        get() = _navigate



    init {
           getMoviesDataFromRemoteDataSource()
        }


    fun getMoviesDataFromRemoteDataSource(){
        viewModelScope.launch {

            repository.insertInTheaterMoviesToRoom()
            repository.insertTopRatedToRoom()
            repository.insertBoxOfficeToRoom()
            repository.insertComingSoonMoviesToRoom()
        }
    }
    fun getComingSoon():LiveData<List<MovieDetail>>{
        return repository.getComingSoonMoviesFromRoom()
    }
    fun getInTheater():LiveData<List<MovieDetail>>{
        return repository.getInTheaterMoviesFromRoom()

    }
    fun getTopRated():LiveData<List<MovieDetail>>{
        return repository.getTopRatedFromRoom()

    }
    fun getBoxOffice():LiveData<List<MovieDetail>> {
        return repository.getBoxOfficeFromRoom()

    }
    fun onItemClicked(movieDetail: MovieDetail) {
        _navigate.value = movieDetail

    }


}