import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bibzpole.R
import com.example.bibzpole.fileUtils.Screenes
import com.example.bibzpole.ui.theme.pink
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController,modifier: Modifier = Modifier) {
    val content = LocalContext.current
    Box(modifier = modifier.fillMaxSize().background(color = pink),
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.sp),
            modifier = modifier.size(100.dp),
            contentDescription = "splash")
    }
    LaunchedEffect(key1 = true) {
        delay(1400)
        val pref = content.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE)
        val login = pref.getBoolean("Login",false)
        val start = pref.getBoolean("Start",false)
        if (start){
            if (login){
                navController.navigate(Screenes.homeScreen.route){
                    navController.popBackStack()
                }
            }else{
                navController.navigate(Screenes.loginScreen.route){
                    navController.popBackStack()
                }
            }

        }else{
            navController.navigate(Screenes.GetStarted.route){
                navController.popBackStack()
            }
        }

    }
}

