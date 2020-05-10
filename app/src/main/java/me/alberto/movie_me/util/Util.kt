package me.alberto.movie_me.util

import me.alberto.movie_me.domain.model.Movie

fun List<Movie>.getFavourites(list: List<Movie>): List<Movie> {
   return map {
        if (it in list){
            it.isFav = true
        }
       it
    }
}