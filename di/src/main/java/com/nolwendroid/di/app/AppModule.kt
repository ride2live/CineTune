package com.nolwendroid.di.app

import android.content.Context
import android.content.SharedPreferences
import com.nolwendroid.cinetune.BuildConfig
import com.nolwendroid.core.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providePrefs (@ApplicationContext appContext: Context) : SharedPreferences {
        return appContext.getSharedPreferences(appContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    }
    @Provides
    @Singleton
    fun provideTmdbApiKey(): String {
        return BuildConfig.TMDB_API_KEY // ✅ Теперь ключ доступен через DI
    }
    @Provides
    @Singleton
    fun provideLastFmApiKey(): String {
        return BuildConfig.LASTFM_API_KEY // ✅ Теперь ключ доступен через DI
    }
    @Provides
    @Singleton
    fun provideLastFmApiSecretKey(): String {
        return BuildConfig.LASTFM_API_SECRET_KEY // ✅ Теперь ключ доступен через DI
    }

}