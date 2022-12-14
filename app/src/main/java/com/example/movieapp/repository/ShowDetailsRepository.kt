package com.example.movieapp.repository

import com.example.movieapp.data.models.ShowDetailsResponse
import com.example.movieapp.data.network.ApiService
import javax.inject.Inject

class ShowDetailsRepository
    @Inject
    constructor(
        private val apiService: ApiService
    ){

    suspend fun getDetailsByID(id: Int): ShowDetailsResponse?{
        val call = apiService.getDetailsByID(id)
        return if(call.isSuccessful) call.body() else null
    }
}