package il.co.gonisch.moviesphere.data

import com.google.gson.annotations.SerializedName


/**
 * @property id the genre id provided by the api.
 * @property name the name of the genre.
 */
data class Genre(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)