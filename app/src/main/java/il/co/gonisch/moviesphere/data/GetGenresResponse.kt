package il.co.gonisch.moviesphere.data

import com.google.gson.annotations.SerializedName

/**
 * @property genres array of returned genres from the TMDB api.
 */
data class GetGenresResponse(
    @SerializedName("genres") var genres: ArrayList<Genre> = arrayListOf()
)
