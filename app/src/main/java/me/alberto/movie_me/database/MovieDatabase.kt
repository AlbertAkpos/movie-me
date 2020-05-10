package me.alberto.movie_me.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.alberto.movie_me.domain.model.Movie


@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        private var INSTANCE: MovieDatabase? = null
        fun get(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}