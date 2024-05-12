import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bibzpole.MusicViewmodel
import com.example.bibzpole.R
import com.example.bibzpole.dataModel.ResultsItem
import com.example.bibzpole.ui.theme.blue
import com.example.bibzpole.ui.theme.pink
import com.google.gson.Gson


@Composable
fun MusicDetails(
    viewmodel: MusicViewmodel,
    navigation: NavHostController,
    modifier: Modifier = Modifier,
    trackId: String
) {
        LaunchedEffect(key1 = true) {
            viewmodel.searchSongsbyrackId(trackId)
    }
    val user by viewmodel.getLogUsers.collectAsState(initial = emptyList() )
    val data by viewmodel.musicsTrack.collectAsState(initial = ResultsItem() )
    val process by viewmodel.progress.collectAsState(initial = false )
    val context = LocalContext.current
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = blue)
    ) {
        val (toTop, bottomButton) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(blue)
                .constrainAs(toTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier.size(5.dp))
            Text(
                text = "Details",
                modifier = modifier.fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Row(modifier = modifier.fillMaxWidth()){

                Box(modifier = modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp),
                        contentAlignment = Alignment.CenterStart) {
                        IconButton(onClick = { navigation.popBackStack() },modifier = modifier
                            .size(62.dp)
                            .padding(horizontal = 8.dp)) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Fav" ,
                            modifier
                                .size(32.dp),
                            tint = Color.Black)
                    }

                }

                    Box(modifier = modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp),
                        contentAlignment = Alignment.CenterEnd) {
                        IconButton(onClick = {
                            viewmodel.addFav(data,user = user[0].Id){
                                Toast.makeText(context,"Added to Favourite", Toast.LENGTH_LONG).show()

                            }
                        },modifier = modifier
                            .size(62.dp)
                            .padding(horizontal = 8.dp)) {
                        Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "Fav" ,
                            modifier
                                .size(32.dp),
                            tint = Color.Black)
                    }

                }            }
            Spacer(modifier.size(5.dp))

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(blue)
                .fillMaxHeight()
                .constrainAs(bottomButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top, margin = 250.dp)
                },
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {

            Box(modifier = modifier
                .background(color = pink)
                .fillMaxSize(), contentAlignment = Alignment.TopCenter){
                if (process) {
                    Box(modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        CircularProgressIndicator(
                            modifier = Modifier.padding(vertical = 25.dp),
                            strokeWidth = 5.dp,
                            color = blue
                        )
                    }
                }
                AsyncImage(
                    model = data.artworkUrl100,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.sp),
                    placeholder = painterResource(R.drawable.band),
                    modifier = modifier
                        .size(200.dp)
                        .padding(vertical = 10.dp)
                        .align(Alignment.TopCenter)
                )
                Column(modifier = modifier
                    .fillMaxSize()
                    .padding(top = 240.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )) {
                    Text(text = data.trackName.toString(),
                        fontFamily = FontFamily(
                            Font(R.font.theme_regular)
                        ),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxWidth())
                    Text(text = data.collectionName.toString(),
                        fontFamily = FontFamily(
                            Font(R.font.theme_regular)
                        ),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth())
                    Text(text = "ArtistName : ${data.artistName}",
                        fontFamily = FontFamily(
                            Font(R.font.theme_regular)
                        ),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth())
                    Text(text = "Release Date : ${data.releaseDate}",
                        fontFamily = FontFamily(
                            Font(R.font.theme_regular)
                        ),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth())

                    Spacer(modifier = modifier.size(5.dp))
                    Text(text = "link : ${data.collectionViewUrl}",
                        fontFamily = FontFamily(
                            Font(R.font.theme_regular)
                        ),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth())


                }
            }
        }

    }

}

