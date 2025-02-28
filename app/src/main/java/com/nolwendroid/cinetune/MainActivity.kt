package com.nolwendroid.cinetune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nolwendroid.cinetune.ui.theme.CineTuneTheme
import com.nolwendroid.core.navigation.AppNavigation
import com.nolwendroid.core.navigation.AppNavigator
import com.nolwendroid.core.navigation.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var navigationProvider: NavigationProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTuneTheme {
                val navController = rememberNavController()
                navigator.setNavController(navController)

          //      AppNavigation(navController, navigationProvider)
                   //setAppNAvigation(navController, navigator, navigationProvider)
            }


            //AppNavigation()
        }
    }

//    @Composable
//    private fun setAppNAvigation(
//        navController: NavHostController,
//        navigator: AppNavigator,
//        navigation: NavigationProvider) {
//        AppNavigation(navigator = navigator, navController = navController, navigationProvider = navigation)
//    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CineTuneTheme {
        Greeting("Android")
    }
}