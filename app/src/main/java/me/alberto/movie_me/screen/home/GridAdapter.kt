package me.alberto.movie_me.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.alberto.movie_me.databinding.MovieItemBinding
import me.alberto.movie_me.domain.model.Movie

class GridAdapter(private val clickListener: MovieClickListener): ListAdapter<Movie, RecyclerView.ViewHolder>(DiffCallback()) {
    class DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id  == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
           return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MovieViewHolder -> holder.bind(getItem(position), clickListener, position)
        }
    }

    class MovieViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(
            movie: Movie,
            clickListener: MovieClickListener,
            position: Int
        ){
            binding.movie = movie
            binding.clickListener = clickListener
            binding.position = position
        }

        companion object {
            fun from (parent: ViewGroup): MovieViewHolder {
                val view = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MovieViewHolder(view)
            }
        }
    }


    class MovieClickListener(private val clickListener:(movie: Movie, position: Int ) -> Unit){
        fun onClick(movie: Movie, position: Int) {
            clickListener(movie, position)
        }

    }
}