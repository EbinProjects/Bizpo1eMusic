import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.bibzpole.MusicViewmodel
import com.example.bibzpole.R
import com.example.bibzpole.fileUtils.Screenes
import com.example.bibzpole.ui.theme.blue
import com.example.bibzpole.ui.theme.lightBlue
import com.example.bibzpole.ui.theme.orange
import com.example.bibzpole.ui.theme.pink
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.net.URLEncoder


@Composable
fun HomePage(viewModel: MusicViewmodel, navController: NavHostController,modifier: Modifier = Modifier) {

    val content = LocalContext.current
   val loginUser by viewModel.getLogUsers.collectAsState(initial = emptyList())
   val music by viewModel.musics.collectAsState(initial = emptyList())
    var serach by remember { mutableStateOf("") }
    val progress by viewModel.progress.collectAsState(false)
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = pink)
    ) {
        val (toTop, bottomButton,extraIcon) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .background(pink)
                .constrainAs(toTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {
            Column(modifier = modifier
                .background(color = orange)
                .fillMaxSize()){
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier.padding(horizontal = 15.dp, vertical = 10.dp)){
                    Image(painter = painterResource(id = R.drawable.mic),
                        modifier = modifier
                            .size(35.dp)
                            .background(color = lightBlue),
                        contentDescription = "background")
                    Text(text = " Hi,\n ${
                        if(loginUser.isNotEmpty())
                        loginUser[0].name else ""}",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.theme_semibold)),
                            fontSize = 22.sp
                        )
                    )
                }
                Box(modifier = modifier
                    .background(color = orange)
                    .fillMaxSize(), contentAlignment = Alignment.Center){
                    Image(painter = painterResource(id = R.drawable.gitar),
                        modifier = modifier.size(330.dp),
                        contentDescription = "gitar")

                }
            }
        }
        Row(modifier = modifier.constrainAs(extraIcon){
            top.linkTo(parent.top, margin = 10.dp)
            end.linkTo(parent.end)
        }){
            IconButton(onClick = {navController.navigate(Screenes.Favlist.route)},modifier = modifier
                .size(62.dp)
                .padding(horizontal = 8.dp)) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Fav" ,
                    modifier.size(32.dp),
                    tint = Color.Black)
            }
            IconButton(onClick = {
                viewModel.logout(loginUser[0].Id)
                navController.navigate(Screenes.loginScreen.route){
                navController.popBackStack()
            } },modifier = modifier
                .size(62.dp)
                .padding(horizontal = 8.dp)) {
                Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "lof" ,
                    modifier.size(32.dp),
                    tint = Color.Black)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(pink)
                .constrainAs(bottomButton) {
                    top.linkTo(toTop.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 8.dp),
                value = serach,
                onValueChange ={
                   serach=it
                } ,
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "", tint = blue) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text ,
                    imeAction = ImeAction.None
                ) ,
                trailingIcon = {
                    if (serach.isNotEmpty()) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "",tint =  Color(0xFFC53333),modifier = Modifier
                            .padding(end = 5.dp)
                            .offset(x = 10.dp)
                            .clickable {
                               viewModel.searchSongs(serach)
                            })
                    }
                },
                shape = RoundedCornerShape(4.dp),
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Transparent,
                    focusedContainerColor = lightBlue,
                    unfocusedContainerColor = lightBlue,
                    disabledContainerColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Black,
                ),
                singleLine = true,
                placeholder = { Text(text = "Search...",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.theme_semibold)),
                        fontSize = 16.sp
                    )) }
            )
            if(progress){
                CircularProgressIndicator(
                    modifier = Modifier.padding(vertical = 25.dp),
                    strokeWidth = 4.dp,
                    color = blue
                )

            }else{
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(music.size) {item ->
                        CardItems(music[item]){
                            if (it){
                                if (music[item].trackId.toString().isNotEmpty()) {
                                    navController.navigate(Screenes.Details.withArguments(music[item].trackId.toString()))
                                }else{
                                    Toast.makeText(content,"Data Error!",Toast.LENGTH_SHORT).show()
                                }
                            }                        }
                    }
                }
            }

        }
    }

}



