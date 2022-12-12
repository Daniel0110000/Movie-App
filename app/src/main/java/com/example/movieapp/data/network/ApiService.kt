package com.example.movieapp.data.network

import com.example.movieapp.constants.ApplicationConstants.ALL_URL
import com.example.movieapp.constants.ApplicationConstants.SEARCH_URL
import com.example.movieapp.data.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_URL)
    suspend fun getAllMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET(SEARCH_URL)
    suspend fun searchMovie(@Query("q") q: String): Response<MovieResponse>

}