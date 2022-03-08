package com.project.pop_flake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.pop_flake.databinding.MovieItemBinding
import com.project.pop_flake.model.MovieDetail

class MoviesAdapter  (val onClickListener: MovieClickListener) :
    androidx.recyclerview.widget.ListAdapter<MovieDetail, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { movie?.let { onClickListener.onClick(it) } }

    }

    class MovieViewHolder private constructor(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie:MovieDetail) {
            binding.movie = movie
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieDetail>() {
    override fun areItemsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
        return oldItem == newItem
    }
}

class MovieClickListener(val onClick: (movieDetail:MovieDetail) -> Unit)
