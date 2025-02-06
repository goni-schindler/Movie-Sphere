package il.co.gonisch.moviesphere.api

import il.co.gonisch.moviesphere.data.GetGenresResponse
import il.co.gonisch.moviesphere.data.GetMoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String,
    ): GetGenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenreId(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int
    ): GetMoviesResponse
    
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): TmdbApi {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TmdbApi::class.java)
        }
    }
}