package com.example.movieapp.repository

import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.data.network.ApiService
import javax.inject.Inject

class MovieRepository
    @Inject
    constructor(
        private val apiService: ApiService
    ){

    suspend fun getAllMovies(page: Int): MovieResponse?{
        return try {
            val call = apiService.getAllMovies(page)
            if(call.isSuccessful) call.body() else null
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}