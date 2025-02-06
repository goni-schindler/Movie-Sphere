package il.co.gonisch.moviesphere.data

import com.google.gson.annotations.SerializedName

data class GetGenresResponse(
    @SerializedName("genres") var genres: ArrayList<Genre> = arrayListOf()
)
