package com.example.movieapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.constants.ApplicationConstants.BASE_URL
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.data.room.FavoritesDao
import com.example.movieapp.data.room.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providerFavoritesDao(@ApplicationContext context: Context): FavoritesDao{
        return Room.databaseBuilder(
            context,
            FavoritesDatabase::class.java,
            "favorites_database"
        ).allowMainThreadQueries().build().getFavoritesDao()
    }
}