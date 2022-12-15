package com.example.movieapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(favorites: Favorites)

    @Query("delete from favorites_table where movie_id =:movieId")
    suspend fun deleteFavoriteMovie(movieId: Int)

    @Query("select * from favorites_table")
    fun getAllFavoritesMovies(): LiveData<List<Favorites>>

    @Query("select * from favorites_table where movie_id =:movieId")
    fun checkFavoriteMovie(movieId: Int): LiveData<List<Favorites>>

}