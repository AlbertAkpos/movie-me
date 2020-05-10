package me.alberto.movie_me.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.alberto.movie_me.domain.model.Movie

@JsonClass(generateAdapter = true)
data class NetworkMovieContainer (
    val page: Long,

    @Json(name = "total_results")
    val totalResults: Long,

    @Json(name = "total_pages")
    val totalPages: Long,

    val results: List<Result>
)

@JsonClass(generateAdapter = true)
data class Result (
    val popularity: Double,

    @Json(name = "vote_count")
    val voteCount: Long,

    val video: Boolean,

    @Json(name = "poster_path")
    val posterPath: String,

    val id: Long,
    val adult: Boolean,

    @Json(name = "backdrop_path")
    val backdropPath: String,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "genre_ids")
    val genreIDS: List<Long>,

    val title: String,

    @Json(name = "vote_average")
    val voteAverage: Double,

    val overview: String,

    @Json(name = "release_date")
    val releaseDate: String
)

fun NetworkMovieContainer.asDomainModel(): List<Movie> {
   return results.map {
        Movie(
            id = it.id,
            poster = it.posterPath,
            banner = it.backdropPath,
            title = it.title,
            overview = it.overview,
            adult = it.adult,
            language = it.originalLanguage,
            rating = it.voteAverage,
            releaseDate = it.releaseDate
        )
    }
}