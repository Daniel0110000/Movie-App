package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.room.Favorites
import com.squareup.picasso.Picasso

class RecyclerFavoritesAdapter(private val favoritesList: List<Favorites>, private val listener: MovieClickListener): RecyclerView.Adapter<RecyclerFavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_rows_design, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.print(position)
    }

    override fun getItemCount(): Int = favoritesList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageMovie: ImageView = itemView.findViewById(R.id.image_movie)
        private val movieName: TextView = itemView.findViewById(R.id.movie_name)

        fun print(position: Int){

            Picasso
                .get()
                .load(favoritesList[position].movieImage)
                .into(imageMovie)

            movieName.text = favoritesList[position].movieName

            itemView.setOnClickListener{
                listener.onItemClick(favoritesList[position].movieId)
            }

        }
    }

}