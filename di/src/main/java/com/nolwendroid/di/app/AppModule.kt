package com.nolwendroid.di.app

import android.content.Context
import android.content.SharedPreferences
import com.nolwendroid.cinetune.BuildConfig
import com.nolwendroid.cinetune.navigation.NavigationProviderImpl
import com.nolwendroid.core.R
import com.nolwendroid.core.navigation.AppNavigator
import com.nolwendroid.core.navigation.NavigationProvider
import dagger.Binds
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
    @Provides
    @Singleton
    fun provideAppNavigator(): AppNavigator {
        return AppNavigator()
    }

//    @Provides
//    @Singleton
//    fun  provideProvider (): NavigationProvider {
//        return NavigationProviderImpl()
//    }
//    @Binds
//    @Singleton
//    abstract fun bindNavigationProvider(impl: NavigationProviderImpl): NavigationProvider


}