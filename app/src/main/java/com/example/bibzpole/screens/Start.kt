import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bibzpole.MusicViewmodel
import com.example.bibzpole.R
import com.example.bibzpole.fileUtils.Screenes
import com.example.bibzpole.ui.theme.blue
import com.example.bibzpole.ui.theme.lightBalck
import com.example.bibzpole.ui.theme.lightBlue


@Composable
fun GetStart(navController: NavHostController, viewmodel: MusicViewmodel,modifier: Modifier = Modifier) {
    val content = LocalContext.current
    val pref = content.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val login = pref.getBoolean("Login",false)
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = lightBlue)
        .padding(horizontal = 15.dp, vertical = 18.dp),
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){
            Image(painter = painterResource(id = R.drawable.music),
                modifier = modifier.size(32.dp),
                contentDescription = "background")
            Text(text = " Musical",
                style = TextStyle(
                    color = blue,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.theme_semibold)),
                    fontSize = 20.sp
                )
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(text = "Let the music take \nyou away...",
            modifier = modifier.fillMaxWidth(),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.theme_semibold)),
                fontSize = 28.sp
            ))
        Spacer(modifier = modifier.height(20.dp))
        Image(painter = painterResource(id = R.drawable.musicbox),
            modifier = modifier
                .width(304.dp).padding(horizontal = 5.dp)
                .height(438.dp),
            contentDescription = "box")
        Spacer(modifier = modifier.height(20.dp))
        Box(modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Card(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(70.dp)
                    .clickable {
                        viewmodel.clickStart()
                        if (login){
                            navController.navigate(Screenes.homeScreen.route){
                                navController.popBackStack()
                            }
                        }else{
                            navController.navigate(Screenes.loginScreen.route){
                                navController.popBackStack()
                            }
                        }
                    },
                colors = CardDefaults.cardColors(lightBalck),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
            ) {
                Icon(modifier = modifier.fillMaxSize().padding(20.dp),
                    imageVector = Icons.Filled.ArrowForward, contentDescription ="Arrow",
                    tint = Color.White)
            }

        }
    }

}

