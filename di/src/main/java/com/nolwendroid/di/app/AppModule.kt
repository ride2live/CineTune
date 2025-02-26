package com.nolwendroid.di.app

import android.content.Context
import android.content.SharedPreferences
import com.nolwendroid.core.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun providePrefs (@ApplicationContext appContext: Context) : SharedPreferences {
        return appContext.getSharedPreferences(appContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    }

}