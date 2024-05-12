import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.bibzpole.MusicViewmodel
import com.example.bibzpole.R
import com.example.bibzpole.fileUtils.Screenes
import com.example.bibzpole.ui.theme.blue
import com.example.bibzpole.ui.theme.pink
import com.example.bibzpole.ui.theme.purple
import com.google.gson.Gson


@Composable
fun Favourite(viewmodel: MusicViewmodel, navigation: NavHostController,modifier: Modifier = Modifier) {
  val content = LocalContext.current
   val list by viewmodel.getFavLists.collectAsState(initial = emptyList())
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = pink)
    ) {
        val (toTop, bottomButton,Back) = createRefs()
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
            Box(modifier = modifier
                .background(color = purple)
                .fillMaxSize(), contentAlignment = Alignment.Center){
                Image(painter = painterResource(id = R.drawable.fav),
                    modifier = modifier.size(330.dp),
                    contentDescription = "fav")
            }
        }
        IconButton(onClick = { navigation.popBackStack()},modifier = modifier
            .size(38.dp)
            .constrainAs(Back) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start)
            }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription ="Back",
                tint = Color.White)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(pink)
                .constrainAs(bottomButton) {
                    top.linkTo(toTop.bottom, margin = 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier.size(5.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(list.size) {music ->
                    CardItems(list[music]){
                        if (it){
                            if (list[music].trackId.toString().isNotEmpty()) {
                                navigation.navigate(Screenes.Details.withArguments(list[music].trackId.toString()))
                            }else{
                                Toast.makeText(content,"Data Error!", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                }
            }


        }
    }

}


