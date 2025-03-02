package com.nolwendroid.cinetune.di.localproperties

import android.util.Log
import com.nolwendroid.cinetune.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KeysModule {
    @Provides
    @Singleton
    @Named("LastFmApiKey")
    fun provideLastFmApiKey(): String {
        return BuildConfig.LASTFM_API_KEY
    }

    @Provides
    @Singleton
    @Named("TmdbApiKey")
    fun provideTMDBApiKey(): String {
        Log.d("Hilt-Debug", "🔍 TmdbApiKey: ${BuildConfig.TMDB_API_KEY}")
        Log.d("Hilt-Debug", "✅ TmdbApiKey успешно создан!")
        return BuildConfig.TMDB_API_KEY
    }

    @Provides
    @Singleton
    @Named("KinopoiskApiKey")
    fun provideKinopoiskApiKey(): String {
        Log.d("Hilt-Debug", "🔍 TmdbApiKey: ${BuildConfig.KINOPOISK_API_KEY}")
        Log.d("Hilt-Debug", "✅ TmdbApiKey успешно создан!")
        return BuildConfig.KINOPOISK_API_KEY
    }
}