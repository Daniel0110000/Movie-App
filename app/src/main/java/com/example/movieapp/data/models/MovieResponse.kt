package com.example.movieapp.data.models

data class MovieResponse(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: List<TvShow>
)