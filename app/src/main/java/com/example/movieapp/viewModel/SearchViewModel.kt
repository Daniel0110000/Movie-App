package com.example.movieapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    val movieName = MutableLiveData<String>()
    val searchResult = MutableLiveData<MovieResponse?>()

    init {
        movieName.value = ""
    }

    fun searchMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            if (movieName.value.toString().isNotEmpty()) {
                val data = searchRepository.searchMovie(movieName.value.toString())
                withContext(Dispatchers.Main) {
                    searchResult.value = data!!
                }
            } else {
                withContext(Dispatchers.Main) {
                    searchResult.value = null
                }
            }
        }
    }

}