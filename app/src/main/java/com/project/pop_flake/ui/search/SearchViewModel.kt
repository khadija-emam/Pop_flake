package com.project.pop_flake.ui.search

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
class SearchViewModel @Inject constructor(val repository: Repository) :ViewModel()
  {

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _navigate = SingleLiveEvent<MovieDetail?>()
    val navigate: SingleLiveEvent<MovieDetail?>
        get() = _navigate

      private val _errorMessage = MutableLiveData<String>()
      val errorMessage: LiveData<String>
          get() = _errorMessage

      private val _moviesList = MutableLiveData<List<MovieDetail>>()
      val moviesList: LiveData<List<MovieDetail>>
          get() = _moviesList

       fun search(title:String){
         viewModelScope.launch {
             _progress.value = true
             val result = repository.searchByTitle(title)
             try {
                 if (result!= null) {
                 _progress.value=false
                  _moviesList.value=result!!
                 }
                 }catch (e:Exception){
                _errorMessage.value=e.message
             }
         }
      }
      fun onItemClicked(movieDetail: MovieDetail) {
          _navigate.value = movieDetail

      }



  }