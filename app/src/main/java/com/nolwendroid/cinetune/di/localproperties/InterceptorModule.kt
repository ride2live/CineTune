package com.nolwendroid.cinetune.di.localproperties
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