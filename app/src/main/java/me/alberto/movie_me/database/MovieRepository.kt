package me.alberto.movie_me.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.alberto.movie_me.domain.model.Movie

class MovieRepository(private val database: MovieDatabase) {
    fun getFavs(): LiveData<List<Movie>> {
        return database.movieDao.getFavs()
    }

    suspend fun deleteFav(movie: Movie) {
        database.movieDao.deleteFav(movie)
    }

    suspend fun addFav(movie: Movie) {
        database.movieDao.addFav(movie)
    }
}