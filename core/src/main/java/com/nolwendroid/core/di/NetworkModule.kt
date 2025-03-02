package com.nolwendroid.di

import android.util.Log
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

//    @Provides
//    @Singleton
//    @LastFmInterceptor
//    fun provideLastFmInterceptor(@Named("LastFmApiKey") apiKey: String): Interceptor {
//        return provideApiKeyInterceptor(apiKey)
//    }
//
    @Provides
    @Singleton
    @TmdbInterceptor
    fun provideTmdbInterceptor(@Named("TmdbApiKey") apiKey: String): Interceptor {
        return provideApiKeyInterceptor(apiKey)
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
//    @Provides
//    @Singleton
//    @LastFmClient
//    fun provideLastFmOkHttpClient(@LastFmInterceptor interceptor: Interceptor): OkHttpClient {
//        return provideOkHttpClient(interceptor)
//    }

    @Provides
    @Singleton
    @TmdbClient
    fun provideTmdbOkHttpClient(@TmdbInterceptor interceptor: Interceptor): OkHttpClient {
        Log.d("Hilt-Debug", "✅ TmdbClient успешно создан!")
        return provideOkHttpClient(interceptor)
    }
    private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        Log.d("Hilt-Debug", "✅ provideOkHttpClient успешно создан!")
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // ✅ Логируем тело запроса и ответа
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

//    @Provides
//    @Singleton
//    @Named("LastFmRetrofit")
//    fun provideLastFmRetrofit(@LastFmClient client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(LAST_FM_BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    @Named("TmdbRetrofit")
    fun provideTmdbRetrofit(@TmdbClient client: OkHttpClient): Retrofit {
        Log.d("Hilt-Debug", "✅ Retrofit создан в core")
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideLastFmApi(@Named("LastFmRetrofit") retrofit: Retrofit): LastFmApi {
//        return retrofit.create(L::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideTmdbApi(@Named("TmdbRetrofit") retrofit: Retrofit): TmdbApiService {
//        return retrofit.create(TmdbApiService::class.java)
//    }
}

