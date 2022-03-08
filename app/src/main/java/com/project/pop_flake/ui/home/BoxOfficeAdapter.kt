package com.project.pop_flake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.pop_flake.databinding.BoxOfficeItemBinding
import com.project.pop_flake.model.MovieDetail

class BoxOfficeAdapter  (val onClickListener: MovieClickListener) :
    androidx.recyclerview.widget.ListAdapter<MovieDetail, BoxOfficeAdapter.BoxOfficeMovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeMovieViewHolder {
        return BoxOfficeMovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BoxOfficeMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { movie?.let { onClickListener.onClick(it) } }

    }

    class BoxOfficeMovieViewHolder private constructor(val binding: BoxOfficeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieDetail) {
            binding.movie = movie
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): BoxOfficeMovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BoxOfficeItemBinding.inflate(layoutInflater, parent, false)
                return BoxOfficeMovieViewHolder(binding)
            }
        }
    }
}
