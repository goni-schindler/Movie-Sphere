package il.co.gonisch.moviesphere.data

import com.google.gson.annotations.SerializedName

/**
 * @property page the api returns movies by pages because there is a shot tones of data
 * @property movies list of movies returned for a specific genre
 */
data class GetMoviesResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var movies: ArrayList<Movie> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("total_results") var totalResults: Int
)
