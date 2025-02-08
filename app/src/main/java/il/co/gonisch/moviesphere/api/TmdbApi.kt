package il.co.gonisch.moviesphere.api

import com.google.gson.GsonBuilder
import il.co.gonisch.moviesphere.BuildConfig
import il.co.gonisch.moviesphere.data.GetGenresResponse
import il.co.gonisch.moviesphere.data.GetMoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface TmdbApi {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Response<GetGenresResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenreId(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int = 1
    ): Response<GetMoviesResponse>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w154"
        fun create(): TmdbApi {
            val gsonBuilder = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, TmdbDateConverter())
                .create()

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build()
                .create(TmdbApi::class.java)
        }
    }
}