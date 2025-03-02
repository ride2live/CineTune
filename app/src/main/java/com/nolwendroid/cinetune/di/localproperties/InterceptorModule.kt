package com.nolwendroid.cinetune.di.localproperties

import android.util.Log
import com.nolwendroid.di.CoreModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object InterceptorModule {
//
//    @Provides
//    @Singleton
//    @CoreModule.TmdbInterceptor
//    fun provideTmdbInterceptor(@Named("TmdbApiKey") apiKey: String): Interceptor {
//        Log.d("Hilt-Debug", "✅ TmdbInterceptor успешно создан!")
//
//        return Interceptor { chain ->
//            val newRequest = chain.request().newBuilder()
//                .addHeader("Authorization", "Bearer $apiKey")
//                .build()
//            chain.proceed(newRequest)
//        }
//    }
//}