package com.example.bibzpole

import Favourite
import GetStart
import HomePage
import LoginPage
import MusicDetails
import SignuPapge
import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bibzpole.fileUtils.Screenes
import com.example.bibzpole.ui.theme.BibzpoleTheme
import com.example.bibzpole.ui.theme.pink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BibzpoleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = pink
                ) {
                    val navController = rememberNavController()
                    MyAppNavHost ( navController = navController)
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewmodel: MusicViewmodel = hiltViewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screenes.Splash.route
    ) {
        composable(route = Screenes.Splash.route) {
            SplashScreen (navController = navController)
        }
        composable(route = Screenes.GetStarted.route) {
            GetStart (navController = navController, viewmodel = viewmodel)
        }
        composable(route = Screenes.loginScreen.route) {
            LoginPage (navController = navController, viewmodel = viewmodel)
        }
        composable(route = Screenes.Signup.route) {
            SignuPapge (navController = navController, viewmodel = viewmodel)
        }
        composable(route = Screenes.homeScreen.route) {
            HomePage (navController = navController, viewModel = viewmodel)
        }
        composable(route= Screenes.Favlist.route )  {
            Favourite(viewmodel = viewmodel,navigation =navController)
        }
        composable(route= Screenes.Details.route + "/{Data}",
            arguments = listOf(
                navArgument("Data"){
                    type = NavType.StringType
                    defaultValue=""
                    nullable=true
                }

            )) {detetails ->
            MusicDetails(trackId = detetails.arguments?.getString("Data") ?:"",viewmodel = viewmodel,navigation =navController)
        }

    }

}

