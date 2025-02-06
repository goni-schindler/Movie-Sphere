package il.co.gonisch.moviesphere.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import il.co.gonisch.moviesphere.api.TmdbApi
import il.co.gonisch.moviesphere.data.MoviesRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideTmdbApi(): TmdbApi {
        return TmdbApi.create()
    }

    @Singleton
    @Provides
    fun provideMoviesRepository(api: TmdbApi) = MoviesRepository(api)
}