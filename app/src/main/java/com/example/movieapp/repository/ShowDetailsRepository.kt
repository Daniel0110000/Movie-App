package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.data.models.ShowDetailsResponse
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.data.room.Favorites
import com.example.movieapp.data.room.FavoritesDao
import javax.inject.Inject

class ShowDetailsRepository
    @Inject
    constructor(
        private val apiService: ApiService,
        private val favoritesDao: FavoritesDao
    ){

    suspend fun getDetailsByID(id: Int): ShowDetailsResponse?{
        val call = apiService.getDetailsByID(id)
        return if(call.isSuccessful) call.body() else null
    }

    fun checkFavoriteMovie(movieId: Int): LiveData<List<Favorites>> = favoritesDao.checkFavoriteMovie(movieId)

    suspend fun addFavoriteMovie(favorites: Favorites){
        favoritesDao.addFavoriteMovie(favorites)
    }

    suspend fun deleteFavoriteMovie(movieId: Int){
        favoritesDao.deleteFavoriteMovie(movieId)
    }



}