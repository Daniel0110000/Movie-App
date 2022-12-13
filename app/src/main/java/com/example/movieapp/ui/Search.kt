package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.RecyclerMoviesAdapter
import com.example.movieapp.data.models.TvShow
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Search : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private var moviesList: ArrayList<TvShow> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.search = viewModel

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView(){
        viewModel.searchResult.observe(viewLifecycleOwner){ movies ->
            binding.recyclerSearch.hasFixedSize()
            binding.recyclerSearch.layoutManager = GridLayoutManager(context, 2)
            moviesList.clear()
            if(movies != null){
                if(movies.tv_shows.isNotEmpty()){
                    binding.searchMovieLayout.visibility = View.GONE
                    binding.recyclerSearch.visibility = View.VISIBLE
                    binding.notFoundLayout.visibility = View.GONE
                    moviesList.addAll(movies.tv_shows)
                    binding.recyclerSearch.adapter = RecyclerMoviesAdapter(moviesList)
                }else{
                    binding.recyclerSearch.visibility = View.GONE
                    binding.notFoundLayout.visibility = View.VISIBLE
                    binding.searchMovieLayout.visibility = View.GONE
                }
            }else{
                binding.searchMovieLayout.visibility = View.VISIBLE
                binding.recyclerSearch.visibility = View.GONE
                binding.notFoundLayout.visibility = View.GONE
            }
        }
    }

}