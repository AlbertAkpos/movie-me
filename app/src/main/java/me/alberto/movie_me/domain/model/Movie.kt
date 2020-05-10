package me.alberto.movie_me.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey
    val id: Long,
    val poster: String,
    val adult: Boolean,
    val banner: String,
    val language: String,
    val title: String,
    val rating: Double,
    val overview: String,
    val releaseDate: String,
    var isFav: Boolean = false
)