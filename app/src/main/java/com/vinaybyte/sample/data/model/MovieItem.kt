package com.vinaybyte.sample.data.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
data class MovieItem(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    companion object {
        fun mock() = MovieItem(
            adult = false,
            backdropPath = "/5a4JdoFwll5DRtKMe7JLuGQ9yJm.jpg",
            genreIds = listOf(
                18,
                878,
                28
            ),
            id = 695721,
            originalLanguage = "en",
            originalTitle = "The Hunger Games: The Ballad of Songbirds & Snakes",
            overview = "64 years before he becomes the tyrannical president of Panem, Coriolanus Snow sees a chance for a change in fortunes when he mentors Lucy Gray Baird, the female tribute from District 12.",
            popularity = 2786.228,
            posterPath = "https://image.tmdb.org/t/p/w342/mBaXZ95R2OxueZhvQbcEWy2DqyO.jpg",
            releaseDate = "2023-11-15",
            title = "The Hunger Games: The Ballad of Songbirds & Snakes",
            video = false,
            voteAverage = 7.218,
            voteCount = 965
        )
    }
}
