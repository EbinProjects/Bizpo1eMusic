import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.bibzpole.MusicViewmodel
import com.example.bibzpole.R
import com.example.bibzpole.ui.theme.blue
import com.example.bibzpole.ui.theme.pink


@Composable
fun SignuPapge(navController: NavHostController, viewmodel: MusicViewmodel,modifier: Modifier = Modifier) {
  val context = LocalContext.current
    var userName by remember {
        mutableStateOf("")
    }
    var Name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = pink)
    ) {
        val (toTop, bottomButton) = createRefs()
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
                .background(color = blue)
                .fillMaxSize(), contentAlignment = Alignment.Center){
                Image(painter = painterResource(id = R.drawable.band),
                    modifier = modifier.size(330.dp),
                    contentDescription = "splash")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(pink)
                .constrainAs(bottomButton) {
                    top.linkTo(toTop.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SignUp Here",
                modifier = modifier.fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier.size(5.dp))
            OutlinedTextField(
                value = Name,
                onValueChange = {user ->
                    Name = user
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                label = {
                    Text(text = " Enter Name")
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                )

            OutlinedTextField(
                value = userName,
                onValueChange = {user ->
                    userName = user
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                label = {
                    Text(text = "Enter Email")
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                maxLines = 1,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                label = {
                    Text(text = "Enter Password")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.None
                ),
                )
            Button(
                onClick = {
                          if (Name.isEmpty()||userName.isEmpty()||password.isEmpty()){
                              Toast.makeText(context,"Fill credentials!", Toast.LENGTH_SHORT).show()
                              return@Button
                          }
                      viewmodel.resgister(user = userName, pass = password, name = Name){
                          if (it) {
                              Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
                          }else{
                              Toast.makeText(context,"Already existing user!", Toast.LENGTH_SHORT).show()

                          }

                      }
                          },
                modifier = modifier
                    .defaultMinSize()
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 8.dp)
                    .background(blue, shape = RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(text = "SignUp")
            }
            Spacer(modifier = modifier.size(5.dp))
            Text(
                text = "Back to login",
                modifier = Modifier.fillMaxWidth().clickable {
                   navController.popBackStack()
                },
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center, style = MaterialTheme.typography.titleMedium
            )

        }
    }

}


