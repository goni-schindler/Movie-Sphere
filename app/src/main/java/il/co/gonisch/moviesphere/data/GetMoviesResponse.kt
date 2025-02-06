package il.co.gonisch.moviesphere.data

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var movies: ArrayList<Movie> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)
