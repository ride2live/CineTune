package com.nolwendroid.cinetune

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.nolwendroid.cinetune.di.navigation.AppNavigator
import com.nolwendroid.cinetune.navigation.AppNavigation
import com.nolwendroid.cinetune.ui.theme.CineTuneTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: AppNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Hilt-Debug", "✅ MainActivity успешно запущена")
        //enableEdgeToEdge()
        setContent {
            CineTuneTheme {
                val navController = rememberNavController()
                AppNavigation(navController, navigator)
            }
        }
    }
}