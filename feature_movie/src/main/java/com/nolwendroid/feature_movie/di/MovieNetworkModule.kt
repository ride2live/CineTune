package com.nolwendroid.feature_movie.di

import android.util.Log
import com.nolwendroid.feature_movie.api.TmdbApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieNetworkModule {

    @Provides
    @Singleton
    fun provideTmdbApi(@Named("TmdbRetrofit") retrofit: Retrofit): TmdbApiService {
        Log.d("Hilt-Debug", "✅ TmdbApiService создан в feature_movie")

        return retrofit.create(TmdbApiService::class.java)
    }
}