package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.ShowDetailsResponse
import com.example.movieapp.data.room.Favorites
import com.example.movieapp.repository.ShowDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel
    @Inject
    constructor(
        private val showDetailsRepository: ShowDetailsRepository
    ): ViewModel(){

    private val movieId = MutableLiveData<Int>()
    private val movieImage = MutableLiveData<String>()
    private val movieName = MutableLiveData<String>()


    fun detailsByID(id: Int): MutableLiveData<ShowDetailsResponse>{
        val details = MutableLiveData<ShowDetailsResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val data = showDetailsRepository.getDetailsByID(id)
            withContext(Dispatchers.Main){
                details.value = data!!
                movieId.value = id
                movieImage.value = data.tvShow.image_thumbnail_path
                movieName.value = data.tvShow.name
            }
        }
        return details
    }

    fun checkFavoriteMovie(id: Int): LiveData<List<Favorites>> = showDetailsRepository.checkFavoriteMovie(id)

    fun addFavoriteMovie() = viewModelScope.launch(Dispatchers.IO) {
        showDetailsRepository.addFavoriteMovie(Favorites(0, movieId.value!!, movieImage.value!!, movieName.value!!))
    }

    fun deleteFavoriteMovie(){
        viewModelScope.launch(Dispatchers.IO) {
            showDetailsRepository.deleteFavoriteMovie(movieId.value!!)
        }
    }



}