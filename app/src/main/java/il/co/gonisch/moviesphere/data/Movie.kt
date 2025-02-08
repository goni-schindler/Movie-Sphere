package il.co.gonisch.moviesphere.data

import com.google.gson.annotations.SerializedName
import il.co.gonisch.moviesphere.api.TmdbApi
import java.time.LocalDate

/**
 * @property releaseDate is provided as string and converted to LocalDate by a custom deserializer
 * @property genreIds list of genre id's the current movie is relevant to
 * @property _posterPath returned as a sub path and contacted under posterPath
 */
data class Movie(
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") private val _posterPath: String,
    @SerializedName("release_date") var releaseDate: LocalDate?,
    @SerializedName("title") var title: String,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int? = null
) {
    val posterPath: String
        get() = "${TmdbApi.IMAGE_BASE_URL}$_posterPath"
}