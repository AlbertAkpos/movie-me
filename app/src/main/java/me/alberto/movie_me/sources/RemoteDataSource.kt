package me.alberto.movie_me.sources

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.alberto.movie_me.domain.model.Movie
import me.alberto.movie_me.network.MovieNetwork
import me.alberto.movie_me.network.NetworkMovieContainer

object RemoteDataSource {
    suspend fun getRemote(): NetworkMovieContainer{
        return withContext(Dispatchers.IO){
            MovieNetwork.movieService.getMovies("d3b018581c65b4ac18d55a61391e87ac")
        }.await()
    }
}