package com.nolwendroid.di

import com.nolwendroid.cinetune.BuildConfig
import com.nolwendroid.core.network.LastFmApi
import com.nolwendroid.core.network.TmdbApi
import com.nolwendroid.di.consts.NetworkConst.LAST_FM_BASE_URL
import com.nolwendroid.di.consts.NetworkConst.TMDB_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Qualifier
    annotation class TmdbInterceptor
    @Qualifier
    annotation class LastFmInterceptor
    @Qualifier
    annotation class TmdbClient
    @Qualifier
    annotation class LastFmClient

    @Provides
    @Singleton
    @LastFmInterceptor
    fun provideLastFmInterceptor(): Interceptor {
        return provideApiKeyInterceptor(BuildConfig.LASTFM_API_KEY)
    }

    @Provides
    @Singleton
    @TmdbInterceptor
    fun provideTmdbInterceptor(): Interceptor {
        return provideApiKeyInterceptor(BuildConfig.TMDB_API_KEY)
    }

    private fun provideApiKeyInterceptor(apiKey: String): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
    @Provides
    @Singleton
    @LastFmClient
    fun provideLastFmOkHttpClient(@LastFmInterceptor interceptor: Interceptor): OkHttpClient {
        return provideOkHttpClient(interceptor)
    }

    @Provides
    @Singleton
    @TmdbClient
    fun provideTmdbOkHttpClient(@TmdbInterceptor interceptor: Interceptor): OkHttpClient {
        return provideOkHttpClient(interceptor)
    }
    private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("LastFmRetrofit")
    fun provideLastFmRetrofit(@LastFmClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LAST_FM_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("TmdbRetrofit")
    fun provideTmdbRetrofit(@TmdbClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLastFmApi(@Named("LastFmRetrofit") retrofit: Retrofit): LastFmApi {
        return retrofit.create(LastFmApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTmdbApi(@Named("TmdbRetrofit") retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }
}

