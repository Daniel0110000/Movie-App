package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.RecyclerEpisodesAdapter
import com.example.movieapp.data.models.Episode
import com.example.movieapp.databinding.ActivityMovieDescriptionBinding

class MovieDescription : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerViewEpisodes()
    }

    private fun initRecyclerViewEpisodes(){
        binding.recyclerEpisodes.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MovieDescription)
            adapter = RecyclerEpisodesAdapter(arrayListOf(
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                ),
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                ),
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                ),
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                ),
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                ),
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                ),
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                ),
                Episode(
                    "12/12/22",
                    1,
                    "Mr robot 1",
                    1
                )
            ))
        }
    }

}