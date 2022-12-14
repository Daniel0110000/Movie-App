package com.example.movieapp.data.network

import com.example.movieapp.constants.ApplicationConstants.ALL_URL
import com.example.movieapp.constants.ApplicationConstants.SEARCH_URL
import com.example.movieapp.constants.ApplicationConstants.SHOW_DETAILS_URL
import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.data.models.ShowDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_URL)
    suspend fun getAllMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET(SEARCH_URL)
    suspend fun searchMovie(@Query("q") q: String): Response<MovieResponse>

    @GET(SHOW_DETAILS_URL)
    suspend fun getDetailsByID(@Query("q") id: Int): Response<ShowDetailsResponse>

}