package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.data.room.Favorites
import com.example.movieapp.data.room.FavoritesDao
import javax.inject.Inject

class FavoritesRepository
    @Inject
    constructor(
        private val favoritesDao: FavoritesDao
    ){

    fun getAllFavoritesMovie(): LiveData<List<Favorites>> {
        return favoritesDao.getAllFavoritesMovies()
    }

}