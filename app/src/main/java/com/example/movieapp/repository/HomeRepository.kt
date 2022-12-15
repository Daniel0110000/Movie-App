package com.example.movieapp.repository

import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.data.network.ApiService
import javax.inject.Inject

class HomeRepository
    @Inject
    constructor(
        private val apiService: ApiService
    ){

     suspend fun getAllMovies(page: Int): MovieResponse?{
         val call = apiService.getAllMovies(page)
         return if(call.isSuccessful) call.body() else null
     }
}