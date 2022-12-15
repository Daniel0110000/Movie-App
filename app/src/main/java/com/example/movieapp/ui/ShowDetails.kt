package com.example.movieapp.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.ImageSlideAdapter
import com.example.movieapp.adapter.RecyclerEpisodesAdapter
import com.example.movieapp.data.models.Episode
import com.example.movieapp.databinding.ActivityShowDetailsBinding
import com.example.movieapp.viewModel.ShowDetailsViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetails : AppCompatActivity() {

    private lateinit var binding: ActivityShowDetailsBinding

    private val viewModel: ShowDetailsViewModel by viewModels()

    private var movieId: Int = 0

    private val imageList: ArrayList<String> = arrayListOf()
    private val episodeList: ArrayList<Episode> = arrayListOf()

    private var isFavoriteMovie: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra("id", 0)

        initUI()

    }

    private fun initUI(){

        binding.backLayout.setOnClickListener {
            onBackPressed()
        }

        viewModel.checkFavoriteMovie(movieId).observe(this){ favorites ->
            isFavoriteMovie = if(favorites.isNotEmpty()){
                binding.addFavoriteMovie.setImageResource(R.drawable.ic_favorite)
                false
            }else{
                binding.addFavoriteMovie.setImageResource(R.drawable.ic_outline_favorite)
                true
            }
        }

        viewModel.detailsByID(movieId).observe(this){ details ->
            if(details != null){
                imageList.addAll(details.tvShow.pictures)
                initImageSlide()
                initGenres(details.tvShow.genres)
                binding.movieName.text = details.tvShow.name
                binding.movieDescription.text = details.tvShow.description
                episodeList.addAll(details.tvShow.episodes)
                initRecyclerViewEpisodes()
            }
        }

        binding.addFavoriteMovie.setOnClickListener {
            if(isFavoriteMovie){
                viewModel.addFavoriteMovie()
            }else{
                viewModel.deleteFavoriteMovie()
            }
        }

    }

    private fun initImageSlide(){
        binding.imagesFromTheFilm.adapter = ImageSlideAdapter(this, imageList)
        binding.indicator.setViewPager(binding.imagesFromTheFilm)
    }

    private fun initGenres(genreList: List<String>){
        genreList.forEach {
            binding.genresLayout.addView(Chip(this).apply {
                text = it
                setChipBackgroundColorResource(R.color.cinder)
                setChipStrokeColorResource(R.color.sandstorm)
                setTextColor(Color.parseColor("#F9B40E"))
                chipStrokeWidth = 1.0F
            })
        }
    }

    private fun initRecyclerViewEpisodes(){
        binding.recyclerEpisodes.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerEpisodesAdapter(episodeList)
        }
    }


}