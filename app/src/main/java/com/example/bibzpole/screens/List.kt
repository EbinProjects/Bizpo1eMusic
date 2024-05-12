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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bibzpole.R
import com.example.bibzpole.dataModel.ResultsItem
import com.example.bibzpole.ui.theme.lightBlue
import com.example.bibzpole.ui.theme.pink



@Composable
fun CardItems(result: ResultsItem,modifier: Modifier = Modifier,navigate : (Boolean) -> Unit){
    Column {
        Box(modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(pink)
            .padding(horizontal = 10.dp).clickable { navigate(true) },
            contentAlignment = Alignment.CenterStart){
            Row (modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
                verticalAlignment = Alignment.CenterVertically){
                Card(
                    modifier = Modifier
                        .size(80.dp)
                        .weight(1f)
                        .background(pink),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    AsyncImage(
                        model = result.artworkUrl100,
                        contentDescription = "image1",
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.sp),
                        placeholder = painterResource(R.drawable.band),
                        modifier  = modifier.fillMaxSize(),
                    )
                }
                Column(modifier = modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .padding(horizontal = 6.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center) {
                    Text(
                        text = result.trackName.toString(),
                        modifier = modifier.fillMaxWidth(),
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.theme_regular)),

                            ),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = result.collectionName.toString(),
                        modifier = modifier.fillMaxWidth(),
                        color = Color.Gray,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.theme_regular)),

                            ),
                    )
                }
            }
        }
Divider(color = Color.LightGray, modifier = modifier.fillMaxWidth(), thickness = 2.dp)
    }
}