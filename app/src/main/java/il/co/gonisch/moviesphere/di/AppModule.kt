package il.co.gonisch.moviesphere.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import il.co.gonisch.moviesphere.api.TmdbApi
import il.co.gonisch.moviesphere.data.MoviesRepository
import javax.inject.Singleton


/**
 * since we have only 2 providers to inject
 * they can all be defined in a single module.
 */
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