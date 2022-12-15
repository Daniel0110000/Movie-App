package com.example.movieapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val homeRepository: HomeRepository
    ): ViewModel() {

    private val oldData = MutableLiveData<MovieResponse?>()

    fun allMovies(page: Int): MutableLiveData<MovieResponse>{
        val movies = MutableLiveData<MovieResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val data = homeRepository.getAllMovies(page)
            withContext(Dispatchers.Main){
                if(data != oldData.value){
                    movies.value = data!!
                    oldData.value = data
                }else{
                    movies.value = movies.value
                }
            }
        }
        return movies
    }
}