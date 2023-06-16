package com.example.jitbackcompose

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.jitbackcompose.ui.theme.JitbackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JitbackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sauth" ){

                        navigation(startDestination = "login", route = "sauth"){

                            composable( route="login"){
                                Column {
                                    Text(text = "Login Screen")
                                    Button(onClick = { navController.navigate("profile") }) {

                                        Text(text = "Go to Profile")

                                    }
                                }
                            }
                            composable( route="auth"){
                                Text(text = "auth Screen")
                            }
                            composable( route="signup"){

                                Column() {

                                    Text(text = "  SIGN UP")
                                    Button(onClick = {navController.navigate("profile") }) {
                                        Text(text = "Go to profile")

                                    }
                                }

                            }
                        }
                        navigation(startDestination = "profile", route = "profileScreen"){

                            composable( route="profile"){
                                Column() {
                                Text(text = " Profile")
                                Button(onClick = {navController.navigate("profileDetails"){
                                    popUpTo("sauth")
                                } }) {
                                    Text(text = "Go to profileDetails")

                                }

                                }
                            }
                            composable( route="profileDetails"){
                                Column() {

                                    Text(text = "  profileDetails")
                                    Button(onClick = {navController.navigate("signup") }) {
                                        Text(text = "Go to signup")

                                    }
                                }
                            }
                            composable( route="profileUpdate"){}
                        }
                    }

                    var isEcpend by remember { mutableStateOf(false) }

//                    messageCard(msg = "dddddddddddddddddddddddd")
//                    Greeting()

//                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier,name:List<String> = List(1000){ "$it"}) {
  

   Surface() {

       var shouldShowOnboarding by rememberSaveable { mutableStateOf("") }

       LazyColumn(modifier =Modifier.padding(4.dp)){
           items(items = name) {

               TextRow(name = it, Expended = {
                   shouldShowOnboarding="$it"
                   Log.d("TAG", "Greeting: ${it.toString()}")
               },shouldShowOnboarding)
           }

       }
   
   }
}

@Composable
fun TextRow(name:String, Expended: (string: String) -> Int, isSelected:String){
    Row(modifier = Modifier.padding(4.dp)) {
        Text(text = "$name: \nhai iam $name, \nfrom Pushavanam, tamilnadu.....", maxLines = if (isSelected == name) Int.MAX_VALUE else 1)
        ElevatedButton(onClick = {
            Log.d("TAG", "TextRow: "+name)
            Expended.invoke(name) }) {
            Text(text = if (isSelected==name)"Hide" else "Show")

        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JitbackComposeTheme {

        messageCard(msg = "dddddddddddddddddddddddd")
    }
}
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
    ,showBackground = true, name = "Dark Mode")
@Composable
fun GreetingPreview2() {
    JitbackComposeTheme {
        Greeting()
//        MessageCard(msg = "dddddddddddddddddddddddd")
    }
}

@Composable
fun messageCard(msg: String) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .padding(2.5.dp)
                .border(1.5.dp, MaterialTheme.colorScheme.error, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )
        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = "msg.author----",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)

            ) {

                Text(
                    text = "-----------------------------------------\ndddddddddddddddddddddddddddddddddddd",
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
                ElevatedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Hello")

                }
            }
        }
    }
}
