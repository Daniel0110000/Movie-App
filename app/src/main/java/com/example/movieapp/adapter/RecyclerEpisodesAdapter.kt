package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.models.Episode

class RecyclerEpisodesAdapter(private val episodesList: ArrayList<Episode>): RecyclerView.Adapter<RecyclerEpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_episodes_rows_design, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.print(position)
    }

    override fun getItemCount(): Int = episodesList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val episodeNumber: TextView = itemView.findViewById(R.id.episode_number)
        private val episodeName: TextView = itemView.findViewById(R.id.episode_name)
        private val episodeAirDate: TextView = itemView.findViewById(R.id.episode_air_date)

        fun print(position: Int){
            episodeNumber.text = "Episode ${episodesList[position].episode} | Season ${episodesList[position].season}"
            episodeName.text = episodesList[position].name
            episodeAirDate.text = episodesList[position].air_date

            val animation = AnimationUtils.loadAnimation(itemView.context, android.R.anim.slide_in_left)
            itemView.animation = animation

        }

    }

}