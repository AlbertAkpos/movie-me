package me.alberto.movie_me.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.alberto.movie_me.R
import me.alberto.movie_me.domain.model.Movie
import me.alberto.movie_me.screen.home.ApiStatus
import me.alberto.movie_me.screen.home.GridAdapter


@BindingAdapter("app:remoteList", "app:localList")
fun setListData(recyclerView: RecyclerView, remoteList: List<Movie>?, localList: List<Movie>? ){
    val adapter = recyclerView.adapter as GridAdapter
    val list = localList?.let { remoteList?.getFavourites(it) }
    adapter.submitList(list)
}

@BindingAdapter("app:imageSrc")
fun setImage(imageView: ImageView, url: String?){
    Glide.with(imageView.context)
        .load("https://image.tmdb.org/t/p/w500$url")
        .apply(RequestOptions()
            .placeholder(R.drawable.loading_animation))
        .error(R.drawable.ic_broken_image)
        .into(imageView)
}

@BindingAdapter("app:progressVisibility")
fun setProgressState(progressBar: ProgressBar, state: ApiStatus?){
    progressBar.visibility = when(state){
        ApiStatus.LOADING -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("app:truncateText")
fun TextView.truncate(string: String){
    text = if (string.length > 23) string.slice(0..23) + "..."  else string
}

@BindingAdapter("app:likeState")
fun ImageView.setImageResource(like: Boolean) {
    setImageResource(if(like)
        R.drawable.ic_like_icon else R.drawable.ic_unlike_icon
    )
}