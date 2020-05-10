package me.alberto.movie_me.screen.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alberto.movie_me.database.MovieRepository
import me.alberto.movie_me.sources.RemoteDataSource
import java.lang.IllegalArgumentException

class HomeViewModelFactory(
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(remoteDataSource, context) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}