package com.example.movieapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class Favorites(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idF") val id: Int = 0,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "movie_image") val movieImage: String,
    @ColumnInfo(name = "movie_name") val movieName: String
)