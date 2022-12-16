package com.example.movieapp.repository

import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.data.network.ApiService
import javax.inject.Inject

class SearchRepository
@Inject
constructor(
    private val apiService: ApiService
) {

    suspend fun searchMovie(q: String): MovieResponse? {
        val call = apiService.searchMovie(q)
        return if (call.isSuccessful) call.body() else null
    }

}