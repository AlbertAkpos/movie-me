package me.alberto.movie_me.database

import androidx.lifecycle.LiveData
import androidx.room.*
import me.alberto.movie_me.domain.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * from movie_table")
    fun getFavs(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFav(movie: Movie)

    @Delete
    suspend fun deleteFav(movie: Movie)
}