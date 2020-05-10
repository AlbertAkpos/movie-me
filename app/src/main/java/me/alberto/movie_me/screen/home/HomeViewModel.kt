package me.alberto.movie_me.screen.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import me.alberto.movie_me.database.MovieDatabase
import me.alberto.movie_me.database.MovieRepository
import me.alberto.movie_me.domain.model.Movie
import me.alberto.movie_me.network.asDomainModel
import me.alberto.movie_me.sources.RemoteDataSource
import java.io.IOException

enum class ApiStatus { LOADING, DONE, ERROR }

class HomeViewModel(
    private val remoteDataSource: RemoteDataSource,
    context: Context
) : ViewModel() {

    private val database = MovieDatabase.get(context)
    private val localDataSource = MovieRepository(database)

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    val localMovies = localDataSource.getFavs()

    private val _clickedItem = MutableLiveData<Int>()
    val clickedItem: LiveData<Int>
        get() = _clickedItem

    init {
        getMovies()
    }

    fun finishedToggle(){
        _clickedItem.value = null
    }


    private fun getMovies() {
        uiScope.launch {
            try {
                _status.value = ApiStatus.LOADING

                _movies.value = remoteDataSource.getRemote().asDomainModel()

                _status.value = ApiStatus.DONE

            } catch (error: IOException) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    suspend fun toggleFav(movie: Movie, position: Int) {

        println("""
            
           position: $position 
            
        """)

        withContext(Dispatchers.IO) {
            if (movie.isFav) {
                localDataSource.deleteFav(movie)
                movie.isFav = false
                return@withContext
            }
            localDataSource.addFav(movie)
            return@withContext
        }

        _clickedItem.value = position
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}