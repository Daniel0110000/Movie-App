package com.example.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.adapter.MovieClickListener
import com.example.movieapp.adapter.RecyclerFavoritesAdapter
import com.example.movieapp.databinding.FragmentFavoritesBinding
import com.example.movieapp.viewModel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Favorites : Fragment(), MovieClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        initUI()

        return binding.root

    }

    private fun initUI() {
        viewModel.allFavoriteMovies().observe(viewLifecycleOwner) { favorites ->
            if (favorites.isNotEmpty()) {
                binding.recyclerFavorites.visibility = View.VISIBLE
                binding.noFavoriteMoviesLayout.visibility = View.GONE
                initRecyclerViewFavorites(favorites)
            } else {
                binding.noFavoriteMoviesLayout.visibility = View.VISIBLE
                binding.recyclerFavorites.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerViewFavorites(favoritesList: List<com.example.movieapp.data.room.Favorites>) {
        binding.recyclerFavorites.apply {
            hasFixedSize()
            layoutManager = GridLayoutManager(context, 2)
            adapter = RecyclerFavoritesAdapter(favoritesList, this@Favorites)
        }
    }

    override fun onItemClick(id: Int) {
        val showDetails = Intent(context, ShowDetails::class.java)
        showDetails.putExtra("id", id)
        startActivity(showDetails)
    }
}