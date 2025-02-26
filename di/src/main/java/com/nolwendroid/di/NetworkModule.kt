package com.nolwendroid.di


import com.nolwendroid.core.network.LastFmApi
import com.nolwendroid.core.network.TmdbApi
import com.nolwendroid.di.consts.NetworkConst
import com.nolwendroid.di.consts.NetworkConst.LAST_FM_BASE_URL
import com.nolwendroid.di.consts.NetworkConst.TMDB_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(NetworkConst.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConst.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConst.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("LastFmRetrofit")
    fun provideLastFmRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LAST_FM_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("TmdbRetrofit")
    fun provideTmdbRetrofit(client: OkHttpClient): Retrofit {
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

//@Provides
//fun provideConverterFactory(): Converter.Factory {
//    return GsonConverterFactory.create()
//}
//
//@Provides
//fun provideRetrofitClient(
//    okHttpClient: OkHttpClient,
//    baseUrl: String,
//    converterFactory: Converter.Factory
//): Retrofit {
//    return Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .client(okHttpClient)
//        .addConverterFactory(converterFactory)
//        .build()
//}
//    @Provides
//    fun provideRestApiService(retrofit: Retrofit): SpotifyApi {
//        return retrofit.create(SpotifyApi::class.java)
//    }
//}
//@Singleton
//class TokenAuthenticator @Inject constructor(
//     private val sharedPreferences: SharedPreferences
//) : Authenticator, Interceptor {
//    override fun authenticate(route: Route?, response: Response): Request? {
//        return response.request.newBuilder()
//            .header(
//                "Authorization", "Bearer ${
//                    sharedPreferences.getString(LocalRepoConst.ACCESS_TOKEN, "")
//                }"
//            )
//            .build()
//    }
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        return chain.proceed(
//            chain.request().newBuilder()
//                .header(
//                    "Authorization", "Bearer ${
//                        sharedPreferences.getString(LocalRepoConst.ACCESS_TOKEN, "")
//                    }"
//                ).build()
//        )
//    }
//
//}
