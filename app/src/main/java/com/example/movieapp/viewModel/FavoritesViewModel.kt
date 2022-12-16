package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.room.Favorites
import com.example.movieapp.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    fun allFavoriteMovies(): LiveData<List<Favorites>> {
        return favoritesRepository.getAllFavoritesMovie()
    }

}