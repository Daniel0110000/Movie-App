package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.models.TvShow
import com.squareup.picasso.Picasso

class RecyclerMoviesAdapter(private val moviesList: ArrayList<TvShow>, val listener: MovieClickListener): RecyclerView.Adapter<RecyclerMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_rows_design, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.print(position)
    }

    override fun getItemCount(): Int = moviesList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageMovie: ImageView = itemView.findViewById(R.id.image_movie)
        private val movieName: TextView = itemView.findViewById(R.id.movie_name)

        fun print(position: Int){

            Picasso
                .get()
                .load(moviesList[position].image_thumbnail_path)
                .into(imageMovie)

            movieName.text = moviesList[position].name

            itemView.setOnClickListener {
                listener.onItemClick(moviesList[position].id)
            }


        }
    }

}