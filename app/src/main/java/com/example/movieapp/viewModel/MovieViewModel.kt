package com.example.movieapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
    @Inject
    constructor(
        private val movieRepository: MovieRepository
    ): ViewModel() {

    fun allMovies(page: Int): MutableLiveData<MovieResponse>{
        val movies = MutableLiveData<MovieResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val data = movieRepository.getAllMovies(page)
            withContext(Dispatchers.Main){
                movies.value = data!!
            }
        }
        return movies
    }
}