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
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Response<GetMoviesResponse>

    @GET("search/movie")
    suspend fun getMoviesByQuery(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Response<GetMoviesResponse>

    companion object {
        // These is not the right place for those Constants!
        // I just wanted to avoid injecting the context.
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w154"
        fun create(): TmdbApi {

            //create custom date converter since the api returns dates in a "pretty" format.
            val gsonBuilder = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, TmdbDateConverter())
                .create()

            // In large scale project a Http logger interceptor should be implemented
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