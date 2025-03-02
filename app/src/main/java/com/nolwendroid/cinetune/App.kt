package com.nolwendroid.cinetune

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CineTuneApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d("Hilt-Debug", "✅ CineTuneApp успешно запущен")

    }
}
