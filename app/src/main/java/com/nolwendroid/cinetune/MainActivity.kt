package com.nolwendroid.cinetune

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.nolwendroid.cinetune.ui.theme.CineTuneTheme
import com.nolwendroid.cinetune.di.navigation.AppNavigator
import com.nolwendroid.cinetune.navigation.AppNavigation
import com.nolwendroid.feature_movie.data.MovieRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
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