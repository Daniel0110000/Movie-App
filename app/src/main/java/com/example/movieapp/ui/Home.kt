package com.example.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.adapter.MovieClickListener
import com.example.movieapp.adapter.RecyclerMoviesAdapter
import com.example.movieapp.constants.ApplicationConstants.TOTAL_PAGES
import com.example.movieapp.data.models.TvShow
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment(), MovieClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var movieList: ArrayList<TvShow> = arrayListOf()
    private lateinit var moviesAdapter: RecyclerMoviesAdapter

    private var currentPage: Int = 1
    private var oldCurrentPage: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root

    }

    private fun initRecyclerView(){
        moviesAdapter = RecyclerMoviesAdapter(movieList, this)
        binding.recyclerAll.apply {
            hasFixedSize()
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if(!binding.recyclerAll.canScrollVertically(1)){
                        oldCurrentPage = currentPage
                        if(currentPage <= TOTAL_PAGES){
                            currentPage += 1
                            if(currentPage != oldCurrentPage){
                                getMovies()
                                binding.secondaryProgressBar.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            })
        }
        getMovies()
    }

    private fun getMovies(){
        viewModel.allMovies(currentPage).observe(viewLifecycleOwner){ movies ->
            binding.mainProgressBar.visibility = View.GONE
            binding.recyclerAll.visibility = View.VISIBLE
            binding.secondaryProgressBar.visibility = View.GONE
            if(movies != null){
                if(movies.tv_shows.isNotEmpty()){
                    movieList.addAll(movies.tv_shows)
                    moviesAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onItemClick(id: Int) {
        val showDetails = Intent(context, ShowDetails::class.java)
        showDetails.putExtra("id", id)
        startActivity(showDetails)
    }

}